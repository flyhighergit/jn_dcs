package com.dhcc.dcs.web;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DispatchingInfo;
import com.dhcc.dcs.entity.TbDepartEquipmenInfo;
import com.dhcc.dcs.entity.TbPoundRecords;
import com.dhcc.dcs.entity.TbWeighingRecord;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DispatchingInfoService;
import com.dhcc.dcs.service.TbWeighingRecordService;
import com.dhcc.dcs.util.HttpDeal;
import com.dhcc.dcs.util.HttpServletRequestUtil;
import com.dhcc.dcs.util.ResourceUtils;
import com.dhcc.dcs.service.TbDepartEquipmenInfoService;
import com.dhcc.dcs.service.TbPoundRecordsService;

/**
 * 
 * 磅秤记录表controller
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-12 lz
 * 	新建文件
 * </pre>
 * 
 * @author 
 * <pre>
 * SD
 * 	lz
 * PG
 *	lz
 * UT
 *
 * MA
 * </pre>
 * @version $Rev$
 *
 * <p/> $Id$
 *
 */
@Controller
@RequestMapping("/tbWeighingRecord")
public class TbWeighingRecordController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TbWeighingRecordService tbWeighingRecordService;
	@Autowired
	private DispatchingInfoService dispatchingInfoService;
	@Autowired
    private TbDepartEquipmenInfoService tbDepartEquipmenInfoService;
	@Autowired
	private TbPoundRecordsService tbPoundRecordsService;
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-12 lz
	 * 	保存
	 * </pre>
	 * 
	 * @param tbWeighingRecord
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public JSONObject save(TbWeighingRecord tbWeighingRecord,String status){
		JSONObject json = new JSONObject();
		
		// 1. 参数分析
		if(StringUtils.isEmpty(tbWeighingRecord.getWeighbridgeCode())
				||StringUtils.isEmpty(tbWeighingRecord.getVehicleCode())) {
			
			json.put("success", false);
			json.put("errMsg", "信息不完整！");
			return json;
		}
		
		// 派车单运行状态（0：未完成  1：已完成）
		status = status==null||"".equals(status)?"0":status;
		
		try {
			// 2. 保存称重记录
			tbWeighingRecord.setCreateDate(new Date());
			tbWeighingRecord.setId(ResourceUtils.getUUID());
			tbWeighingRecordService.add(tbWeighingRecord);
			
			// 3. 更新派单信息状态（是否完成）
			if(!StringUtils.isEmpty(tbWeighingRecord.getDispatchingId())) {
				DispatchingInfo dis = new DispatchingInfo();
				dis.setId(tbWeighingRecord.getDispatchingId());
				dis.setStatus(status);
				dispatchingInfoService.updateStatu(dis);
			}
			
			// 4. 返回信息
			json.put("weightRecordId", tbWeighingRecord.getId());
			json.put("success", true);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			json.put("success", false);
			json.put("errMsg", "生成称重记录失败！");
		}
		
		return json;
	}
	
	/**
	  * 获取称重信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getWeight")
	@ResponseBody
	public JSONObject getWeight(HttpServletRequest request){
		// 称重记录ID和磅秤ID
		String id = HttpServletRequestUtil.getString(request, "id");
		String poundId = HttpServletRequestUtil.getString(request, "poundId");
		
		JSONObject json = new JSONObject();
		
		try {
			TbWeighingRecord twr = tbWeighingRecordService.getById(id);
			
			if(twr!=null&&!StringUtils.isEmpty(id)&&StringUtils.isEmpty(twr.getWeight())) {
				
				// 直到获取到称重相关信息，否则一直请求
				String result = "";
				JSONObject  jsonObject = new JSONObject();
				boolean a = true;
				do {
					result = getWeight(twr);
					jsonObject = JSONObject.parseObject(result);
					a = !jsonObject.getBooleanValue("success");
				}while(a);
				
				Double weight = null;
				
				
				Map<String,Object> map = (Map<String,Object>)jsonObject;
				if(map!=null &&map.containsKey("weightValue")) {
					// 设置重量信息
					weight = ((BigDecimal)map.get("weightValue")).doubleValue();
					if(weight > 0) {
						twr.setWeight(weight);
					}else {
						logger.error("HTTP请求返回数据错误！");
						json.put("success", false);
						json.put("errMsg","HTTP请求返回数据错误！");
						return json;
					}
					
				}else {
					logger.error("HTTP请求返回数据错误！");
					json.put("success", false);
					json.put("errMsg","HTTP请求返回数据错误！");
					return json;
				}
				
				// 将获取的称重信息合并到记录并保存
				Execution<TbWeighingRecord> execution = tbWeighingRecordService.modify(twr);
				if(execution.getState()!=StateEnum.SUCCESS.getState()) {
					json.put("success", false);
					json.put("errMsg", execution.getStateInfo());
					return json;
				}
				
				// 上传(修改)一条称重记录到云中心
				TbPoundRecords poundRecord = new TbPoundRecords();
				if(StringUtils.isEmpty(poundId)) { // 如果没有poundId，说明是第一次称重
					poundRecord.setCompanyCode(twr.getCompanyCode());
					poundRecord.setDispatchingId(twr.getDispatchingId());
					poundRecord.setFirstBalanceCode(twr.getWeighbridgeCode());
					poundRecord.setFirstTime(new Date());
					poundRecord.setFirstWeight(weight);
					poundRecord.setLicensePlate(twr.getVehicleCode());
					
					Execution<TbPoundRecords> pe = tbPoundRecordsService.add(poundRecord);
					if(pe.getState()==StateEnum.SUCCESS.getState()) {
						json.put("poundId", pe.getT().getId());
					}
				}else {
					poundRecord.setId(poundId);
					poundRecord.setSecondCode(twr.getWeighbridgeCode());
					poundRecord.setSecondTime(new Date());
					poundRecord.setSecondWeight(weight);
					
					Execution<TbPoundRecords> pe = tbPoundRecordsService.update(poundRecord);
					if(pe.getState()==StateEnum.SUCCESS.getState()) {
						json.put("poundId", pe.getT().getId());
					}
				}
				
				json.put("result", execution.getT());
				json.put("success", true);
			}else {
				json.put("success", false);
			}
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			json.put("success", false);
			json.put("errMsg", e.getMessage());
		}
		return json;
	}
	
	/**
	 * 获取称重
	 * @param twr
	 * @return
	 * @author zhanglei
	 */
	private String getWeight(TbWeighingRecord twr) {
		// HTTP处理工具类
		HttpDeal hd = new HttpDeal();
		
		// 拼接URL
		TbDepartEquipmenInfo equipment = new TbDepartEquipmenInfo();
        equipment.setEquipmentCode(twr.getWeighbridgeCode());
        List<TbDepartEquipmenInfo> equipmentList = tbDepartEquipmenInfoService.findBySearch(equipment);
        String IP = equipmentList.get(0).getServiceUrl();
        String url = "http://"+IP+"/jn_bc/weightdata/updateWeightdata?";
		url += "vehNum="+twr.getVehicleCode();
		url += "&collieryID="+twr.getCompanyCode();
		url += "&bridgeID="+twr.getWeighbridgeCode();
		url += "&scanningTime="+twr.getCreateDate().getTime();
		
		logger.debug("请求称重的URL："+url);
		
		return hd.get(url);
	}
}

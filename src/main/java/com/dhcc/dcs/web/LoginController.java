package com.dhcc.dcs.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DispatchingInfo;
import com.dhcc.dcs.entity.TbPoundRecords;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DispatchingInfoService;
import com.dhcc.dcs.service.TbDepartEquipmenInfoService;
import com.dhcc.dcs.service.TbPoundRecordsService;
import com.dhcc.dcs.service.TbWeighingRecordService;


/**
 * 
 * 派车信息controller
 */
@Controller
@RequestMapping("/appLogin")
public class LoginController  {

	@Resource
	private  DispatchingInfoService dispatchingInfoService;
	@Resource
	private TbWeighingRecordService tbWeighingRecordService;
	@Resource
    private TbDepartEquipmenInfoService tbDepartEquipmenInfoService;
	@Autowired
	private TbPoundRecordsService tbPoundRecordsService;
	
	/**
	 * 登录页(登录页已集成到apk，无需做路由跳转，方便离线状态下访问)
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@Deprecated
	public String login(HttpServletRequest request){
		
		return "local/login";
	}
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "frontend/index";
	}
	
	/**
	 * 登录校验
	 * @param licensePlate
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loginCheck",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public JSONObject loginCheck(String licensePlate, HttpServletRequest request){
		JSONObject json = new JSONObject();
		
		// 1. 判断传入车牌号是否为空
		if(StringUtils.isEmpty(licensePlate)) {
			json.put("success", false);
			json.put("errMsg", "未获取到车牌号信息！");
			return json;
		}
		
		// 2. 根据车牌号查找正在进行的磅房作业记录
		Execution<TbPoundRecords> pe = tbPoundRecordsService.findUnderWay(licensePlate);
		if(pe.getState()==StateEnum.SUCCESS.getState()) {
			json.put("poundRecordId", pe.getT().getId());
			json.put("dispatchingId", pe.getT().getDispatchingId());
			json.put("carriageId", pe.getT().getCarriageContractId());
			json.put("purchaseId", pe.getT().getPurchaseContractId());
			json.put("state", 4);
			return json;
		}else {
			// 3. 没有正在进行的磅房作业，那么就查询所有未完成的派单
			Execution<DispatchingInfo> de = dispatchingInfoService.findCurrentDispatching(licensePlate);
			if(de.getState()==StateEnum.SUCCESS.getState()) {
				if(de.getList().size()==1) { // 有且仅有一条派单
					json.put("state", 1);
					json.put("dispatchingInfo", de.getList().get(0));
				}else { // 有且有多条派单
					json.put("state", 2);
				}
			}else { // 没有派单
				json.put("state",3);
			}
		}
		
		return json;
	}

}
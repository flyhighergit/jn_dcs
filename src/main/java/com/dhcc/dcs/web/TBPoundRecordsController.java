package com.dhcc.dcs.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.entity.TbPoundRecords;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbPoundRecordsService;
import com.dhcc.dcs.util.HttpServletRequestUtil;
import com.dhcc.dcs.util.OwnGlobals;

@Controller
@RequestMapping("/poundrecord")
public class TBPoundRecordsController {

	@Autowired
	private TbPoundRecordsService tbPoundRecordsService;

	/**
	 * 获取称重记录
	 * 
	 * @param tbPoundRecords
	 * @return
	 */
	@RequestMapping("/getUnderWay")
	@ResponseBody
	public JSONObject getWeightRecord(String licensePlate) {
		JSONObject json = new JSONObject();

		// 1. 判断传入车牌号是否为空
		if (StringUtils.isEmpty(licensePlate)) {
			json.put("success", false);
			json.put("errMsg", "未获取到车牌号信息！");
			return json;
		}

		// 2. 根据车牌号查找正在进行的称重记录
		Execution<TbPoundRecords> execution = tbPoundRecordsService.findUnderWay(licensePlate);
		if (execution.getState() == StateEnum.SUCCESS.getState()) {
			json.put("result", execution.getT());
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}

		return json;
	}

	/**
	 * 获取称重作业记录
	 * 
	 * @param tbPoundRecords
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/getHistory")
	@ResponseBody
	public JSONObject getHistory(TbPoundRecords tbPoundRecords, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		if (tbPoundRecords == null || StringUtils.isEmpty(tbPoundRecords.getLicensePlate())) {
			json.put("success", false);
			json.put("errMsg", "未获取到车牌号信息！");
			return json;
		}

		Execution<Map<String, String>> execution = tbPoundRecordsService.getHistory(tbPoundRecords);

		if (execution.getState() == StateEnum.SUCCESS.getState()) {
			json.put("list", execution.getList());
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}

		return json;
	}

	/**
	 * 手动结束磅房作业
	 * 
	 * @param tbPoundRecords
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("doOff")
	@ResponseBody
	public JSONObject doOff(TbPoundRecords tbPoundRecords, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		tbPoundRecords.setStatus(OwnGlobals.DATA_STAUS_1);
		Execution<TbPoundRecords> execution = tbPoundRecordsService.update(tbPoundRecords);

		if (execution.getState() == StateEnum.SUCCESS.getState()) {
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("errMsg", "结束任务失败！");
		}

		return json;
	}

	/**
	 * 电子货运单列表
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/waybilllist")
	@ResponseBody
	public JSONObject waybilllist(@RequestParam Map<String, Object> map, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
		if(user == null){
			json.put("success", false);
			json.put("errMsg", "当前会话已失效，请重新登录！");
			
			return json;
		}

		map.put("idCard", user.getIdCord());
		
		Execution<Map<String, Object>> execution = tbPoundRecordsService.findByPage(map);
		// 结果处理
		if (execution.getState() == StateEnum.SUCCESS.getState()) {
			json.put("list", execution.getList());
			json.put("count", execution.getCount());
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}

		return json;
	}

	/**
	 * 获取货运单详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/getWayBill")
	@ResponseBody
	public JSONObject getWayBillDetail(HttpServletRequest request){
		JSONObject json = new JSONObject();

		// 磅房作业记录ID
		String poundRecordId = HttpServletRequestUtil.getString(request, "id");

		Map<String,Object> result = tbPoundRecordsService.getWayBill(poundRecordId);
		if(result == null || result.isEmpty()){
			json.put("success", false);
			json.put("errMsg","获取失败！");
		}else {
			json.put("success", true);
			json.put("result", result);
		}

		return json;
	}

}

package com.dhcc.dcs.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.PhoneLicensePlateInfo;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.PhoneLicensePlateInfoService;

@Controller
@RequestMapping("/phonebind")
public class PhoneLicensePlateInfoController {
	@Autowired
	private PhoneLicensePlateInfoService phoneLicensePlateInfoService;
	
	/**
	 * 车牌号与设备绑定
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping("/bind")
	@ResponseBody
	public JSONObject bind (PhoneLicensePlateInfo phone,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		
		Execution<PhoneLicensePlateInfo> execution = phoneLicensePlateInfoService.add(phone);
		if(execution.getState()==StateEnum.SUCCESS.getState()) {
			json.put("success", true);
		}else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}
		
		return json;
	}
	
	/**
	 * 检测设备绑定
	 * @param id
	 * @param licencePlate
	 * @param request
	 * @return
	 */
	@RequestMapping("/check")
	@ResponseBody
	public JSONObject check (String id,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		
		// 查询设备当前绑定信息
		PhoneLicensePlateInfo phone = phoneLicensePlateInfoService.getById(id);
		// 如果有绑定信息，则返回成功
		if(phone!=null&&phone.getLicenseplate()!=null) {
			json.put("success", true);
			json.put("licencePlate", phone.getLicenseplate());
		}else {
			json.put("success", false);
			json.put("errMsg", "未查询到当前设备的绑定信息！");
		}
		
		return json;
	}
	
	/**
	 * 解除绑定
	 * @param licensePlate 车牌号
	 * @param id 设备ID
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/unbind")
	@ResponseBody
	public JSONObject unbind(String id) {
		JSONObject json = new JSONObject();
		
		Execution<PhoneLicensePlateInfo> execution = phoneLicensePlateInfoService.delete(id);
		if(execution.getState() == StateEnum.SUCCESS.getState()) {
			json.put("success", true);
		}else {
			json.put("success", false);
			json.put("errMsg", "未知错误");
		}
		
		return json;
	}
}

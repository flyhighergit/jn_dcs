package com.dhcc.dcs.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DispatchingInfo;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DispatchingInfoService;
import com.dhcc.dcs.util.OwnGlobals;

/**
 * 
 * 派车信息controller
 */
@Controller
@RequestMapping("/dispatchingInfo")
public class DispatchingInfoController  {

	@Resource
	private  DispatchingInfoService dispatchingInfoService;
	
	/**
	 * 手机端派车信息列表(全部)
	 * @param request
	 * @return
	 */
	@RequestMapping("/dispatchinginfoList")
	@ResponseBody
	public JSONObject dispatchinginfoList(DispatchingInfo dispatchingInfo,HttpServletRequest request){
		JSONObject json = new JSONObject();
		List<DispatchingInfo> list = dispatchingInfoService.findBysearch(dispatchingInfo);
		if(list==null||list.size()<1) {
			json.put("success", false);
			json.put("errMsg", "未获取到您的派车信息");
		}else {
			json.put("list", list);
			json.put("success", true);
		}
		
		return json;
	}
	
	/**
	 * 手机端派车信息
	 * @param licensePlate
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getDiapatchingInfoForAppJN")
	@ResponseBody
	public String getDiapatchingInfoForAppJN(String licensePlate,HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("success", true);
		try {
			DispatchingInfo dispatchingInfo = new DispatchingInfo();
			List<DispatchingInfo> dispatchingInfoList = null;
			dispatchingInfo.setLicensePlate(licensePlate);
			dispatchingInfo.setStatus(OwnGlobals.DATA_STAUS_0);
			dispatchingInfoList = dispatchingInfoService.findBysearch(dispatchingInfo);
			json.put("dispatchingInfoList",dispatchingInfoList);
		} catch (Exception e) {
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();	
	}
	
	/**
	 * 获取派车信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getDiapatchingInfoForAppJNById")
	@ResponseBody
	public String getDiapatchingInfoForAppJNById(String id,HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("success", true);
		try {
			DispatchingInfo dispatchingInfo = dispatchingInfoService.getById(id);
			if(ObjectUtils.isEmpty(dispatchingInfo)) {
				
				json.put("success",false);
			}else {
				json.put("dispatchingInfo",dispatchingInfo);
			}
		} catch (Exception e) {
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();	
	}
	
	/**
	 * 判断派单多少
	 * @param licensePlate
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkDispatching")
	@ResponseBody
	public String checkDispatching(String licensePlate,HttpServletRequest request){
		JSONObject json=new JSONObject();
		DispatchingInfo dispatchingInfo=new DispatchingInfo();
		dispatchingInfo.setLicensePlate(licensePlate);
		dispatchingInfo.setStatus(OwnGlobals.DATA_STAUS_0);
		List<DispatchingInfo> dispatchingInfoList = dispatchingInfoService.findBysearch(dispatchingInfo);
		json.put("success", true);
		if(!ObjectUtils.isEmpty(dispatchingInfoList)||dispatchingInfoList.size()<1){
			if(dispatchingInfoList.size()==1) {
				json.put("count", 1);
				json.put("dispatchingInfo", dispatchingInfoList.get(0));
			}else if (dispatchingInfoList.size()>1){
				json.put("count", dispatchingInfoList.size());
				json.put("list", dispatchingInfoList);
			}
		}else {
			json.put("success", false);
			json.put("errMsg", "未获取到正在进行的派单信息");
		}
		
		return json.toString();	
	}
	
	/**
	 * 
	 * @param vehicleId
	 * @param request
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request){
		
		return "frontend/dispatching/dispatching";
	}
	
	/**
	 * 选择派单页路由
	 * @param vehicleId
	 * @param request
	 * @return
	 */
	@RequestMapping("/choose")
	public String choose(HttpServletRequest request){
		
		return "frontend/dispatching/chooseDispatching";
	}
	
	/**
	 * 获取最早创建且未完成派车详情
	 * @param dispatchingInfo
	 * @return
	 */
	/*@RequestMapping("/getUnderWay")
	@ResponseBody
	public JSONObject getUnderWay(DispatchingInfo dispatchingInfo) {
		JSONObject json = new JSONObject();
		
		// 1. 判断车牌号、企业编号是否获取到
		if(StringUtils.isEmpty(dispatchingInfo.getLicensePlate())
				||StringUtils.isEmpty(dispatchingInfo.getCoalRelatedCode())) {
			json.put("success", false);
			json.put("errMsg", "未获取到车牌号或涉煤企业信息！");
			return json;
		}
		
		// 2. 根据车牌号和企业编号，查询当前派单信息
		Execution<DispatchingInfo> execution = dispatchingInfoService.findCurrentDispatching(dispatchingInfo);
		
		// 3. 结果处理
		if(StateEnum.SUCCESS.getState()==execution.getState()) {
			json.put("success", true);
			json.put("map", execution.getMap());
		}else {
			json.put("success", false);
			json.put("errMsg", "查询派单信息失败！");
		}
		
		return json;
	}*/
	
	/**
	 * 承运历史页面路由
	 * @return
	 */
	@RequestMapping("/historylist")
	public String historylist() {
		return "frontend/dispatching/historylist";
	}
	
	/**
	 * 获取承运历史列表数据
	 * @param licensePlate
	 * @return
	 */
	@RequestMapping("/getHistoryList")
	@ResponseBody
	public JSONObject getHistoryList(DispatchingInfo dispatchingInfo) {
		JSONObject json = new JSONObject();
		
		// 1. 判断是否传递车牌号
		if(StringUtils.isEmpty(dispatchingInfo.getLicensePlate())) {
			json.put("success", false);
			json.put("errMsg", "未获取到您的车牌号信息！");
			return json;
		}
		
		// 2. 根据车牌号查找相关已完成的派车单
		dispatchingInfo.setStatus(OwnGlobals.DATA_STAUS_1);
		List<DispatchingInfo> list = dispatchingInfoService.findBysearch(dispatchingInfo);
		if(list==null||list.size()<1) {
			json.put("success", false);
			json.put("errMsg", "未获取到承运历史信息！");
			return json;
		}
		
		json.put("success", true);
		json.put("list", list);
		
		return json;
	}
	
	/**
	 * 承运历史详情页面
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail() {
		return "frontend/dispatching/dispatchingDetail";
	}
	
	/**
	 * 根据派车单ID获取计量单信息
	 * @param dispatchingId
	 * @return
	 */
	@RequestMapping("getDetail")
	@ResponseBody
	public JSONObject getDetail (String dispatchingId) {
		JSONObject json = new JSONObject();
		
		DispatchingInfo dispatchingInfo = dispatchingInfoService.getById(dispatchingId);
		
		if(dispatchingInfo!=null) {
			
			Map<String,String> purchase = new HashMap<String, String>();
			
			if(!StringUtils.isEmpty(dispatchingInfo.getPurchaseInfoId())) {
				purchase = dispatchingInfoService.getPurchaseByPurchaseId(dispatchingInfo.getPurchaseInfoId());
			}else if(!StringUtils.isEmpty(dispatchingInfo.getCarriageContractId())) {
				purchase = dispatchingInfoService.getPurchaseByCarriageId(dispatchingInfo.getCarriageContractId());
			}else {
				json.put("success", false);
				json.put("errMsg", "未查询到购销合同信息！");
				return json;
			}
			
			json.put("success", true);
			json.put("purchase", purchase);
		}else {
			json.put("success", false);
			json.put("errMsg", "查询派单信息失败！");
		}
		
		return json;
	}
	
	/**
	 * 司机手动结束未完成的派单和称重
	 * @param dispatchingId
	 * @param poundId
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("doOff")
	@ResponseBody
	public JSONObject doOff (String dispatchingId,String poundId) {
		JSONObject json = new JSONObject();
		
		boolean result = dispatchingInfoService.off(dispatchingId, poundId);
		
		if(result) {
			json.put("success",true);
		}else {
			json.put("success", false);
			json.put("errMsg", "结束任务失败！");
		}
		
		return json;
	}
	
	/**
	 * 根据车牌号获取购销合同信息
	 * @param licensePlate
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/getPurchaseByLicensePlate")
	@ResponseBody
	public JSONObject getPurchaseByLicensePlate(String licensePlate) {
		JSONObject json = new JSONObject();
		
		Execution<Map<String,String>> execution = dispatchingInfoService.getPurchaseByLicensePlate(licensePlate);
		if(execution.getState() == StateEnum.SUCCESS.getState()) {
			json.put("success", true);
			json.put("list", execution.getList());
		}else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}
		
		return json;
	}
	
}

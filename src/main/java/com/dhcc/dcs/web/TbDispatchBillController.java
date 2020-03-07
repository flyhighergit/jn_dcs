package com.dhcc.dcs.web;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DriverInfo;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.entity.TbDispatchBill;
import com.dhcc.dcs.entity.TbDriverVehicle;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DriverInfoService;
import com.dhcc.dcs.service.TbDispatchBillService;
import com.dhcc.dcs.service.TbDriverVehicleService;
import com.dhcc.dcs.util.HttpServletRequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 派车单controller
 * 
 * <pre>
 * 	历史记录：
 * 	2020-02-19 zhanglei
 * 	新建文件
 * </pre>
 * 
 * @author 
 * <pre>
 * SD
 * 	zhanglei
 * PG
 *	zhanglei
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
@RequestMapping("/tbDispatchBill")
public class TbDispatchBillController  {
	@Autowired
	private TbDispatchBillService tbDispatchBillService;
	@Autowired
	private DriverInfoService driverInfoService;
	@Autowired
	private TbDriverVehicleService tbDriverVehicleService;
	
	/**
	 * 订单列表页面路由
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "/frontend/dispatchbill/dispatchBillList";
	}

	/**
	 * 获取订单信息列表
	 * @param tbDispatchBill
	 * @param request
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JSONObject getList(TbDispatchBill tbDispatchBill,HttpServletRequest request){
		JSONObject json = new JSONObject();

		// 当前登录人基本信息
    	TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
    	// 判断是否session已失效
    	if(user==null) {
    		json.put("success", false);
    		json.put("errMsg", "当前会话失效，请重新登录！");
		}

		// 获取司机信息
		DriverInfo driverInfo = driverInfoService.getByFounderId(user.getId());

		// 获取当前司机绑定的车辆
		TbDriverVehicle params = new TbDriverVehicle();
		params.setDriverId(driverInfo.getId());
		params.setCompanyId(driverInfo.getCompanyId());
		List<TbDriverVehicle> relationlist = tbDriverVehicleService.findBysearch(params);

		// 分页信息
		Integer pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		Integer pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if ((pageIndex < 0) || (pageSize < 0)) {
			json.put("success", false);
			json.put("errMsg", "没有分页信息！");
			return json;
		}

		Execution<TbDispatchBill> execution = tbDispatchBillService.findByLicense(relationlist, tbDispatchBill.getStatus(),pageIndex, pageSize);
		if(execution.getState() == StateEnum.SUCCESS.getState()){
			json.put("success",true);
			json.put("list",execution.getList());
			json.put("count",execution.getCount());
		}else{
			json.put("success",false);
			json.put("errMsg","查询订单失败！");
		}

		return json;
	}

	/**
	 * 订单详情页面
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(){
		return "/frontend/dispatchbill/dispatchBillDetail";
	}

	/**
	 * 订单详情页面
	 */
	@RequestMapping("/getdetail")
	@ResponseBody
	public JSONObject getDetail(String id,HttpServletRequest request){
		JSONObject json = new JSONObject();

		TbDispatchBill bill = tbDispatchBillService.getById(id);
		if(bill == null){
			json.put("success",false);
			json.put("errMsg","没有相关订单！");
		}else {
			json.put("success",true);
			json.put("dispatchBill",bill);
		}

		return json;
	}

	/**
	 * 签收
	 * @param id
	 * @return
	 */
	@RequestMapping("/signfor")
	@ResponseBody
	public JSONObject signfor(TbDispatchBill tbDispatchBill,HttpServletRequest request){
		JSONObject json = new JSONObject();
		// 当前登录人基本信息
    	TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
    	// 判断是否session已失效
    	if(user==null) {
    		json.put("success", false);
    		json.put("errMsg", "当前会话失效，请重新登录！");
		}
		
		// 获取司机信息
		DriverInfo driverInfo = driverInfoService.getByFounderId(user.getId());
		tbDispatchBill.setStatus("01");
		tbDispatchBill.setDriverId(driverInfo.getId());
		tbDispatchBill.setDriverName(driverInfo.getName());

		// 执行签收
		Boolean success = tbDispatchBillService.signfor(tbDispatchBill);
		
		// 结果处理
		if(success){
			json.put("success",true);
		}else {
			json.put("success",false);
		}

		return json;
	}
}

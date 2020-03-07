package com.dhcc.dcs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dao.VehicleInfoDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.entity.VehicleInfo;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DispatchingInfoService;
import com.dhcc.dcs.service.VehicleInfoService;
import com.dhcc.dcs.util.HttpServletRequestUtil;


/**
 * 
 * 车辆信息controller
 */
@Controller
@RequestMapping("/vehicleInfo")
public class VehicleInformationController  {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private VehicleInfoService vehicleInfoService;
	@Resource
	private  DispatchingInfoService dispatchingInfoService;
	@Resource
	private VehicleInfoDao vehicleInfoDao;
	
	/**
	 * 手机端流程待办 新版样式
	 * @param workInfo
	 * @param page
	 * @param flowType
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getInfomationForAppJN",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getInfomationForAppJN(String licensePlate,HttpServletRequest request){
		JSONObject  json = new JSONObject();
		
		try {
			VehicleInfo vehicleInfo=vehicleInfoService.getByLicensePlate(licensePlate);
			if(!ObjectUtils.isEmpty(vehicleInfo)) {
				json.put("vehicleInfo", vehicleInfo);
				json.put("success",true);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			json.put("success",false);
			e.printStackTrace();
		}
		
		return json.toString();
	}
	
	/**
     * 车辆信息新增页面路由
     * 
     * @param request
     * @return
     */
    @RequestMapping("/goAdd")
    public String goAdd(HttpServletRequest rep) {

        return "frontend/vehicle/vehicleInfoAdd";
    }
	
	/**
	 * 
	 * @param vehicleId
	 * @param request
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request){
		
		return"vehicle/vehicleInfo";
	}
	
	/**
     * 新增车辆信息
	* 
	* @return
	*/
	@RequestMapping("/doAdd")
	@ResponseBody
	public JSONObject doAdd(VehicleInfo vehicleInfo,HttpServletRequest request) {
	 JSONObject json = new JSONObject();
	// 当前登录人信息
 	TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
	
	 // 添加车辆信息
	 if (null != vehicleInfo) {
	     try {
	         // 执行添加操作
	    	 vehicleInfo.setFounder(user.getUserName());//创建人
	    	 vehicleInfo.setFounderId(user.getId());//创建人ID
	    	 vehicleInfo.setId(UUID.randomUUID().toString().replace("-", ""));
	    	 vehicleInfo.setCheckStatus("3");//数据状态
	    	 
	         Execution<VehicleInfo> execution = vehicleInfoService.add(vehicleInfo);
	         if (execution.getState() == StateEnum.SUCCESS.getState()) {
	             json.put("success", true);
	             json.put("id", execution.getT().getId());
	         }
	         else {
	             json.put("success", false);
	             json.put("errMsg", execution.getStateInfo());
	         }
	     }
	     catch (Exception e) {
	         json.put("success", false);
	         json.put("errMsg", e.toString());
	         return json;
	     }
	 }
	 else {
	     json.put("success", false);
	     json.put("errMsg", "请输入车辆基本信息");
	 }
	
	 return json;
	
	}
	
	/**
     * 修基本信息
     * @param 
     * @return
     * @author guofei
     */
    @RequestMapping("/doEdit")
    @ResponseBody
    public JSONObject doEdit(VehicleInfo vehicleInfo,HttpServletRequest request) {
    	JSONObject json = new JSONObject();
    	// 当前登录人信息
     	TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
    	
		try {
			if(null != vehicleInfo.getCheckStatus() && vehicleInfo.getCheckStatus().equals("0")) {
				//更新修改时间、修改人ID、修改人
				vehicleInfo.setUpdateTime(new Date());
				vehicleInfo.setModifierId(user.getId());
				vehicleInfo.setModifier(user.getRealName());
			}else if(null != vehicleInfo.getCheckStatus() && vehicleInfo.getCheckStatus().equals("1")){
				vehicleInfo.setCheckerId(user.getId());
				vehicleInfo.setCheckTime(new Date());
			}
			
			vehicleInfoDao.updateNotNull(vehicleInfo);
			 
			json.put("id", vehicleInfo.getId());
			json.put("success", true);
		}
   	    catch (Exception e) {
   	        json.put("success", false);
   	        json.put("errMsg", e.toString());
   	        return json;
   	    }
		 
		return json;
    }
	
	/**
 	* 根据ID获取车辆信息
	* 
	* @param companyId
	* @param request
	* @return
	*/
	@RequestMapping(value = "/chooseVehicleInfo", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject chooseVehicleInfo(String companyId, String driverId,HttpServletRequest request) {
	    JSONObject json = new JSONObject();
	    Map map = new HashMap();
    	
    	try {
    		map.put("driverId", driverId);
    		map.put("companyId", companyId);
    		
    		// 获取车辆信息
    		List<VehicleInfo> list = vehicleInfoService.getByCompanyId(map);
    		
    		json.put("list", list);
			json.put("count", list!=null?list.size():0);
			json.put("success", true);
    	} catch (NumberFormatException e) {
			e.printStackTrace();
			json.put("success", false);
			json.put("errMsg","查询失败");
		}

    	return json;
	}
	
	/**
 	* 根据车牌号获取车辆信息
	* 
	* @param companyId
	* @param request
	* @return
	*/
	@RequestMapping("/getByLicensePlate")
	@ResponseBody
	public JSONObject getByLicensePlate(HttpServletRequest request) {
	    JSONObject json = new JSONObject();
	    List list = new ArrayList();
	    // 车牌号
    	String licensePlate = HttpServletRequestUtil.getString(request, "licensePlate");
	    
    	try {
    		// 获取车辆信息
    		VehicleInfo vehicleInfo = vehicleInfoService.getByLicensePlate(licensePlate);
    		list.add(vehicleInfo);
    		
    		json.put("list", list);
			json.put("count", list!=null?list.size():0);
			json.put("success", true);
    	} catch (NumberFormatException e) {
			e.printStackTrace();
			json.put("success", false);
			json.put("errMsg","查询失败");
		}

    	return json;
	}
	
	/**
     * 验证车牌号是否唯一
	* 
	* @return
	*/
	@RequestMapping("/validLicensePlate")
	@ResponseBody
	public JSONObject validLicensePlate(HttpServletRequest request,String licensePlate) {
		JSONObject json = new JSONObject();
		
		try {
	         // 验证车牌号是否唯一
			int number = vehicleInfoService.validLicensePlate(licensePlate);
			json.put("number", number);
	     }
	     catch (Exception e) {
	         json.put("success", false);
	         json.put("errMsg", e.toString());
	         return json;
	     }
		
		return json;
	}
	
	/**
     * 验证车辆识别代号是否唯一
	* 
	* @return
	*/
	@RequestMapping("/validVin")
	@ResponseBody
	public JSONObject validVin(HttpServletRequest request,String vin) {
		JSONObject json = new JSONObject();
		
		try {
	         // 验证车辆识别代号是否唯一
			int number = vehicleInfoService.validVin(vin);
			json.put("number", number);
	     }
	     catch (Exception e) {
	         json.put("success", false);
	         json.put("errMsg", e.toString());
	         return json;
	     }
		
		return json;
	}
	
	/**
     * 验证发动机号是否唯一
	* 
	* @return
	*/
	@RequestMapping("/validEngineNum")
	@ResponseBody
	public JSONObject validEngineNum(HttpServletRequest request,String engineNum) {
		JSONObject json = new JSONObject();
		
		try {
	         // 验证发动机号是否唯一
			int number = vehicleInfoService.validEngineNum(engineNum);
			json.put("number", number);
	     }
	     catch (Exception e) {
	         json.put("success", false);
	         json.put("errMsg", e.toString());
	         return json;
	     }
		
		return json;
	}
	
}

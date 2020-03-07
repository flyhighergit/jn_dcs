package com.dhcc.dcs.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DriverInfo;
import com.dhcc.dcs.entity.TbDriverVehicle;
import com.dhcc.dcs.entity.VehicleInfo;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DriverInfoService;
import com.dhcc.dcs.service.TbDriverVehicleService;
import com.dhcc.dcs.service.VehicleInfoService;
import com.dhcc.dcs.util.HttpServletRequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 司机-车辆关联关系Controller
 */
@Controller
@RequestMapping("/driverVehicle")
public class TbDriverVehicleController {

	@Autowired
	private TbDriverVehicleService tbDriverVehicleService;
	@Autowired
	private VehicleInfoService vehicleInfoService;
	@Autowired
	private DriverInfoService driverInfoService;

	/**
	 * 列表页路由
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "";
	}

	/**
	 * 条件查询关系信息
	 * 
	 * @param tbDriverVehicle
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JSONObject getList(TbDriverVehicle tbDriverVehicle) {
		JSONObject json = new JSONObject();
		List<TbDriverVehicle> list = tbDriverVehicleService.findBysearch(tbDriverVehicle);
		json.put("list", list);

		return json;
	}

    /**
     * 复杂查询
     * @param tbDriverVehicle
     * @return
     */
    @RequestMapping("/getRelationshipList")
    @ResponseBody
    public JSONObject getRelationshipList(Map<String,String> params){
        JSONObject json = new JSONObject();
        Execution<Map<String,Object>> execution = tbDriverVehicleService.getRelationship(params);
        if(execution.getState() == StateEnum.SUCCESS.getState()){
            json.put("list", execution.getList());
            json.put("success", true);
        }else {
            json.put("success", false);
            json.put("errMsg", execution.getStateInfo());
        }
        
        return json;
    }
    
    /**
     * 
     * @param companyId
     * @return
     */
    @RequestMapping("/getDriverVehicleInfo")
    @ResponseBody
    public JSONObject getDriverVehicleInfo(String companyId,String driverId){
        JSONObject json = new JSONObject();
        Map<String,String> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("driverId", driverId);
        
        TbDriverVehicle tbDriverVehicle = new TbDriverVehicle();
        tbDriverVehicle.setCompanyId(companyId);
        List<TbDriverVehicle> driverVehicleList = tbDriverVehicleService.findBysearch(tbDriverVehicle);
        
        List<VehicleInfo> vehicleInfolist = vehicleInfoService.getVehicleByCompanyId(map);
        
        json.put("driverVehicleList", driverVehicleList);
        json.put("vehicleInfolist", vehicleInfolist);
        json.put("success", true);
        
        return json;
    }
    
	/**
	 * 新增 司机-公司-车辆 信息
	 * 
	 * @return
	 */
	@RequestMapping("/doAdd")
	@ResponseBody
	public JSONObject doAdd(TbDriverVehicle tbDriverVehicle, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		// 司机Id
		String driverId = HttpServletRequestUtil.getString(request, "driverId");
		// 绑定的公司的Id
		String companyId = HttpServletRequestUtil.getString(request, "companyId");
		// 绑定的车辆的Id
		String vehicleId = HttpServletRequestUtil.getString(request, "vehicleId");

		try {
			// 执行添加操作
			tbDriverVehicle.setId(UUID.randomUUID().toString().replace("-", ""));
			tbDriverVehicle.setDriverId(driverId);
			tbDriverVehicle.setCompanyId(companyId);
			tbDriverVehicle.setVehicleId(vehicleId);
			tbDriverVehicle.setCreateTime(new Date());
			tbDriverVehicle.setUpdateTime(new Date());
			tbDriverVehicle.setStatus("0");

			tbDriverVehicleService.add(tbDriverVehicle);

			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			json.put("errMsg", e.toString());
		}

		return json;
	}

	/**
	 * 删除 司机-公司-车辆 信息
	 * 
	 * @return
	 */
	@RequestMapping("/deleteByVehicleId")
	@ResponseBody
	public JSONObject deleteByVehicleId(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Map<String,String> map = new HashMap<>();
		
		// 绑定车辆司机用户的Id
		String vehicleId = HttpServletRequestUtil.getString(request, "vehicleId");
		// 要解绑的车辆的Id
		String driverId = HttpServletRequestUtil.getString(request, "driverId");

		try {
			map.put("driverId", driverId);
			map.put("vehicleId", vehicleId);
			
			// 执行删除操作
			tbDriverVehicleService.deleteByVehicleId(map);

			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			json.put("errMsg", e.toString());
		}

		return json;
	}

	/**
	 * 修改 司机-公司-车辆 信息
	 * 
	 * @return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public JSONObject doUpdate(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();

		// 要设为默认的车辆的Id
		String vehicleId = HttpServletRequestUtil.getString(request, "vehicleId");
		// 设置数据状态为默认
		String status = HttpServletRequestUtil.getString(request, "status");
		// 当前司机用户所属公司Id
		String companyId = HttpServletRequestUtil.getString(request, "companyId");
		// 当前司机信息的Id
		String driverId = HttpServletRequestUtil.getString(request, "driverId");

		try {
			map.put("vehicleId", vehicleId);
			map.put("status", status);
			map.put("companyId", companyId);
			map.put("driverId", driverId);

			// 执行修改操作
			int number = tbDriverVehicleService.updateStatus(map);
			if (number < 1) {
				json.put("success", false);
				json.put("errMsg", "操作失败！");
				return json;
			}

			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			json.put("errMsg", e.toString());
		}

		return json;
	}

	/**
	 * 获取司机和车辆信息，用于生成二维码
	 * @param request
	 * @return
	 */
	@RequestMapping("/getQRCodeInfo")
	@ResponseBody
	public JSONObject getDriverAndVehicleInfo(HttpServletRequest request){
		JSONObject json = new JSONObject();

		// 获取司机和车辆的ID
		String driverId = HttpServletRequestUtil.getString(request,"driverId");
		String vehicleId = HttpServletRequestUtil.getString(request,"vehicleId");

		// 查询司机和车辆详细信息
		DriverInfo driverInfo = driverInfoService.getById(driverId);
		VehicleInfo vehicleInfo = vehicleInfoService.getById(vehicleId);

		if(driverInfo == null){
			json.put("success",false);
			json.put("errMsg", "未查询到司机详细信息！");
			return json;
		}

		if(vehicleInfo == null){
			json.put("success",false);
			json.put("errMsg", "未查询到车辆详细信息！");
			return json;
		}

		json.put("success",true);
		json.put("driverInfo",driverInfo);
		json.put("vehicleInfo",vehicleInfo);

		return json;
	}

}
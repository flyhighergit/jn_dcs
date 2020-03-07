package com.dhcc.dcs.service;

import java.util.List;
import java.util.Map;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.VehicleInfo;

/**
 * 
 * 车辆信息service接口
 */
public interface VehicleInfoService{
	

	/**
	 * wjp
	 * 通过id查询
	 */
	public VehicleInfo getById(String mindId);
	/**
	 * 
	 * 通过车牌号查询
	 */
	public VehicleInfo getByLicensePlate(String licensePlate);
	/**
	 * wjp
	 * 通过所属公司id查询
	 */
	public List<VehicleInfo> getByCompanyId(Map map);
	
	public List<VehicleInfo> getVehicleByCompanyId(Map map);
	
	/**
	 * 
	 * 验证车牌号是否唯一
	 */
	int validLicensePlate(String licensePlate);
	
	/**
	 * 
	 * 新增
	 */
	Execution<VehicleInfo> add(VehicleInfo vehicleInfo);
	
	/**
	 * 
	 * 验证车辆识别代号是否唯一
	 */
	int validVin(String vin);
	
	/**
	 * 
	 * 验证发动机号是否唯一
	 */
	int validEngineNum(String engineNum);
}

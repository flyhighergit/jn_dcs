package com.dhcc.dcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dhcc.dcs.entity.VehicleInfo;



/**
 * 
 * 车辆信息方法类
 * 
 */
public interface VehicleInfoDao {
  
	public VehicleInfo getById(String VehicleInfoId);
	
	public VehicleInfo getByLicensePlate(String licensePlate);
	
	public List<VehicleInfo> getByCompanyId(Map map);
	
	public List<VehicleInfo> getVehicleByCompanyId(Map map);
	
	public int validLicensePlate(String licensePlate);
	
	public int add(VehicleInfo vehicleInfo);
	
	public int updateNotNull(VehicleInfo vehicleInfo);
	
	public int validVin(String vin);
	
	public int validEngineNum(String engineNum);
	
}
package com.dhcc.dcs.dao;

import java.util.List;
import java.util.Map;

import com.dhcc.dcs.entity.TbDriverVehicle;

public interface TbDriverVehicleDao {
    int add(TbDriverVehicle tbDriverVehicle);

	int updateNotNull(TbDriverVehicle tbDriverVehicle);

    int delete(String id);
    
    List<TbDriverVehicle> findBySearch(TbDriverVehicle tbDriverVehicle);

    TbDriverVehicle getById(String id);

	List<Map<String, Object>> getRelationship(Map<String, String> params);
	
	public int deleteByVehicleId(Map map);
	
	public int updateStatus(Map<String,String> map);
	
	TbDriverVehicle getDefaultVehicle(String driverId);
} 
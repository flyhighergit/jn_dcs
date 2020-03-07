package com.dhcc.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dhcc.dcs.entity.DriverInfo;


public interface DriverInfoDao {

	int updateNotNull(DriverInfo driverInfo);

	List<DriverInfo> findByPage(@Param("driverInfo") DriverInfo driverInfo,int rowIndex, int pageSize);
	
	int queryCount(DriverInfo driverInfo);
	
	int insert(DriverInfo driverInfo);
	
	DriverInfo getById(String id);
	
	DriverInfo getByFounderId(String id);
	
	DriverInfo getByIdCord(String id);

	List<DriverInfo> findBySearch(DriverInfo driverInfo);

	int delete(String id);
	
	public int unbindDriverInfo(String id);
	
	public int bindDriverInfo(DriverInfo driverInfo);
}

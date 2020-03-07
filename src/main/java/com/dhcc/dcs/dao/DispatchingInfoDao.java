package com.dhcc.dcs.dao;

import java.util.List;
import java.util.Map;

import com.dhcc.dcs.entity.DispatchingInfo;

/**
 * 
 * 车辆信息方法类
 * 
 */
public interface DispatchingInfoDao {
	
	public List<DispatchingInfo>  findList(DispatchingInfo dispatchingInfo);
   
	public DispatchingInfo getById(String id);
   
	public DispatchingInfo find(DispatchingInfo dispatching);
   
	public int updateStatu(DispatchingInfo dispatchingInfo);
   
	public List<DispatchingInfo> findCurrent(String licensePlate);
   
	public Map<String, Object> findGauge(String dispatchingId);
   
	public Map<String, String> getPurchase(String carriagetId, String purchaseId);

	public Map<String, String> getPurchaseByPurchaseId(String purchaseId);

	public Map<String, String> getPurchaseByCarriageId(String carriageId);

	public List<Map<String, String>> getPurchaseByLicensePlate(String licensePlate);
   
}
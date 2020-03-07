package com.dhcc.dcs.service;

import java.util.List;
import java.util.Map;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DispatchingInfo;

/**
 * 
 * 车辆信息service接口
 */
public interface DispatchingInfoService{
	
 	/**
 	 * 查询所有符合条件的派车单
 	 * @param dispatchingInfo
 	 * @return
 	 */
	public List<DispatchingInfo> findBysearch(DispatchingInfo dispatchingInfo);
	
	/**
	 * 根据ID获取派车信息
	 * @param id
	 * @return
	 */
	public DispatchingInfo getById(String id);
	
	/**
	 * 更新派车单状态
	 * @param dispatchingInfo
	 * @return
	 */
	public Execution<DispatchingInfo>  updateStatu(DispatchingInfo dispatchingInfo);
	
	/**
	 * 查询当前正在进行的派车信息	
	 * @param dispatchingInfo
	 * @return
	 */
	public Execution<DispatchingInfo> findCurrentDispatching(String licensePlate);
	
	public Execution<DispatchingInfo> findGauge(String dispatchingId);
	
	public boolean off(String dispatchingId, String poundId);
	
	public Map<String, String> getPurchaseByPurchaseId(String purchaseInfoId);
	
	public Map<String, String> getPurchaseByCarriageId(String carriageContractId);

	public Execution<Map<String, String>> getPurchaseByLicensePlate(String licensePlate);

}

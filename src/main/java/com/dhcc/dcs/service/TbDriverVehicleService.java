package com.dhcc.dcs.service;

import java.util.List;
import java.util.Map;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbDriverVehicle;

/**
 * 
 * 司机——车辆——承运单位关联service接口
 */
public interface TbDriverVehicleService {
	
 	/**
      * 查询所有符合条件的关系
      * @param tbDriverVehicle
      * @return
      */
	public List<TbDriverVehicle> findBysearch(TbDriverVehicle tbDriverVehicle);
	
	/**
	 * 根据ID获取关联关系
	 * @param id
	 * @return
	 */
	public TbDriverVehicle getById(String id);
	
	/**
     * 更新关联关系信息
     * @param tbDriverVehicle
     * @return
     */
	public Execution<TbDriverVehicle>  update(TbDriverVehicle tbDriverVehicle);

    /**
     * 复杂条件查询关联关系
     * @param licensePlate
     * @return
     */
    public Execution<Map<String, Object>> getRelationship(Map<String, String> params);
    
    public void remove(String id);
    
    /**
	 * 新增
	 * @param tbDriverVehicle
	 * @return
	 */
	public int add(TbDriverVehicle tbDriverVehicle);
	
	/**
	 * 根据车辆Id删除
	 * @param vehicleId
	 * @return
	 */
	public int deleteByVehicleId(Map map);
	
	public int updateStatus(Map<String,String> map);

}

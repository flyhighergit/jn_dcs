package com.dhcc.dcs.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.dcs.dao.TbDriverVehicleDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbDriverVehicle;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbDriverVehicleService;

/**
 * 
 * 派车信息service实现类
 * 
 */
@Service("tbDriverVehicleService")
public class TbDriverVehicleServiceImpl implements TbDriverVehicleService {

    @Autowired
    private TbDriverVehicleDao tbDriverVehicleDao;

    @Override
    public List<TbDriverVehicle> findBysearch(TbDriverVehicle tbDriverVehicle) {
        
        return tbDriverVehicleDao.findBySearch(tbDriverVehicle);
    }

    @Override
    public TbDriverVehicle getById(String id) {
        return tbDriverVehicleDao.getById(id);
    }

    @Override
    @Transactional
    public Execution<TbDriverVehicle> update(TbDriverVehicle tbDriverVehicle) {
        if(tbDriverVehicle == null || "".equals(tbDriverVehicle.getId())){
            return new Execution<>(StateEnum.EMPTY);
        }

        int effectNum = tbDriverVehicleDao.updateNotNull(tbDriverVehicle);
        if(effectNum <= 0) {
            return new Execution<>(StateEnum.INNER_ERROR);
        }

        return new Execution<>(StateEnum.SUCCESS);
    }

    @Override
    public Execution<Map<String, Object>> getRelationship(Map<String, String> params) {
        List<Map<String,Object>> list = tbDriverVehicleDao.getRelationship(params);
        if(list == null || list.size()<1){
            return new Execution<>(StateEnum.INNER_ERROR);
        }
        return new Execution<>(StateEnum.SUCCESS,list);
    }

    @Override
    public void remove(String id) {
        tbDriverVehicleDao.delete(id);
    }
    
    @Override
    public int add(TbDriverVehicle tbDriverVehicle) {
        return tbDriverVehicleDao.add(tbDriverVehicle);
    }
    
    @Override
    public int deleteByVehicleId(Map map) {
        return tbDriverVehicleDao.deleteByVehicleId(map);
    }
    
    @Override
    public int updateStatus(Map<String,String> map) {
    	//String companyId = map.get("companyId");
    	String driverId = map.get("driverId");
    	
    	//当前司机是否已经设置过默认车辆
    	TbDriverVehicle tbDriverVehicle = tbDriverVehicleDao.getDefaultVehicle(driverId);
    	if(null != tbDriverVehicle) {
    		TbDriverVehicle driverVehicle = new TbDriverVehicle();
    		driverVehicle.setId(tbDriverVehicle.getId());
    		driverVehicle.setStatus("0");
    		
    		tbDriverVehicleDao.updateNotNull(driverVehicle);
    	}
    	
        return tbDriverVehicleDao.updateStatus(map);
    }
}
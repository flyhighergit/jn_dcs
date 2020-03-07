package com.dhcc.dcs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.dcs.dao.VehicleInfoDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.VehicleInfo;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.VehicleInfoService;



/**
 * 
 * 车帘信息service实现类
 * 
 */
@Service("vehicleInfoService")
public class VehicleInfoServiceImpl implements VehicleInfoService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private VehicleInfoDao vehicleInfoDao;
	
	@Override
	@Transactional
	public VehicleInfo getById(String id) {
		
		return vehicleInfoDao.getById(id);
	}

	@Override
	@Transactional
	public VehicleInfo getByLicensePlate(String licensePlate) {
		return vehicleInfoDao.getByLicensePlate(licensePlate);
	}
	
	@Override
	@Transactional
	public List<VehicleInfo> getByCompanyId(Map map) {
		return vehicleInfoDao.getByCompanyId(map);
	}
	
	@Override
	@Transactional
	public List<VehicleInfo> getVehicleByCompanyId(Map map) {
		
		return vehicleInfoDao.getVehicleByCompanyId(map);
	}
 	
	@Override
	public int validLicensePlate(String licensePlate) {
		
		return vehicleInfoDao.validLicensePlate(licensePlate);
	}
	
	@Override
	public Execution<VehicleInfo> add(VehicleInfo vehicleInfo) {
		// 空值判断
		if (vehicleInfo != null) {
			// 设置上默认属性
			vehicleInfo.setCreateTime(new Date());
			
			try {
				// 创建商品信息
				int effectedNum = vehicleInfoDao.add(vehicleInfo);
				if (effectedNum <= 0) {
					throw new Exception("新增车辆信息失败");
				}
			} catch (Exception e) {
				logger.error("新增车辆信息失败！"+e.getMessage(),e);
				return new Execution<VehicleInfo>(StateEnum.INNER_ERROR);
			}
			
			return new Execution<VehicleInfo>(StateEnum.SUCCESS, vehicleInfo);
		} else {
			// 传参为空则返回空值错误信息
			return new Execution<VehicleInfo>(StateEnum.EMPTY);
		}
		
	}
	
	@Override
	public int validVin(String vin) {
		
		return vehicleInfoDao.validVin(vin);
	}
	
	@Override
	public int validEngineNum(String engineNum) {
		
		return vehicleInfoDao.validEngineNum(engineNum);
	}
}
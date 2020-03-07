package com.dhcc.dcs.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.dhcc.dcs.dao.DispatchingInfoDao;
import com.dhcc.dcs.dao.TbPoundRecordsDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DispatchingInfo;
import com.dhcc.dcs.entity.TbPoundRecords;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DispatchingInfoService;
import com.dhcc.dcs.util.OwnGlobals;


/**
 * 
 * 派车信息service实现类
 * 
 */
@Service("DispatchingInfoService")
public class DispatchingInfoServiceImpl implements DispatchingInfoService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DispatchingInfoDao dispatchingInfoDao;
	@Autowired
	private TbPoundRecordsDao tbPoundRecordsDao;
	
	@Override
	@Transactional
	public List<DispatchingInfo> findBysearch(DispatchingInfo dispatchingInfo) {
		return dispatchingInfoDao.findList(dispatchingInfo);
	}
	
	@Override
	@Transactional
	public DispatchingInfo getById(String id) {
		if(StringUtils.isEmpty(id)) {
			return null;
		}
		return dispatchingInfoDao.getById(id);
	}

	@Override
	@Transactional
	public Execution<DispatchingInfo> updateStatu(DispatchingInfo dispatchingInfo) {
		if(dispatchingInfo==null || StringUtils.isEmpty(dispatchingInfo.getId())) {
			return new Execution<DispatchingInfo>(StateEnum.EMPTY);
		}
		
		dispatchingInfo.setUpdateTime(new Date());
		
		int effectNum = dispatchingInfoDao.updateStatu(dispatchingInfo);
		if(effectNum < 1) {
			return new Execution<DispatchingInfo>(StateEnum.INNER_ERROR,dispatchingInfo);
		}
		
		return new Execution<DispatchingInfo>(StateEnum.SUCCESS,dispatchingInfo);
	}
	
	@Override
	public Execution<DispatchingInfo> findCurrentDispatching(String licensePlate) {
		if(StringUtils.isEmpty(licensePlate)) {
			return new Execution<DispatchingInfo>(StateEnum.EMPTY);
		}
		
		List<DispatchingInfo> result = null;
		try {
			result = dispatchingInfoDao.findCurrent(licensePlate);
		} catch (Exception e) {
			logger.error("查询派单详情失败！"+e.getMessage(),e);
			return new Execution<DispatchingInfo>(StateEnum.INNER_ERROR);
		}
		
		if(result==null||result.size()<1) {
			return new Execution<DispatchingInfo>(StateEnum.INNER_ERROR);
		}
		
		return new Execution<DispatchingInfo>(StateEnum.SUCCESS,result);
	}

	@Override
	public Execution<DispatchingInfo> findGauge(String dispatchingId) {
		
		if(StringUtils.isEmpty(dispatchingId)) {
			return new Execution<DispatchingInfo>(StateEnum.EMPTY);
		}
		
		Map<String,Object> result = dispatchingInfoDao.findGauge(dispatchingId);
		if(result==null||result.isEmpty()) {
			return new Execution<DispatchingInfo>(StateEnum.INNER_ERROR);
		}
		
		return new Execution<DispatchingInfo>(StateEnum.SUCCESS,result);
	}
	
	@Override
	@Transactional
	public boolean off(String dispatchingId,String poundId){
		boolean result1 = false;
		boolean result2 = false;
		
		if(!StringUtils.isEmpty(dispatchingId)) {
			// 将派单设置为完成
			DispatchingInfo dispatchingInfo = new DispatchingInfo();
			dispatchingInfo.setId(dispatchingId);
			dispatchingInfo.setStatus(OwnGlobals.DATA_STAUS_1);
			
			int effectNum = dispatchingInfoDao.updateStatu(dispatchingInfo);
			
			if(effectNum > 1) {
				result1 = true;
			}
		}
		
		if(!StringUtils.isEmpty(poundId)) {
			// 将称重记录结束
			TbPoundRecords tbPoundRecords = new TbPoundRecords();
			tbPoundRecords.setId(poundId);
			tbPoundRecords.setSecondTime(new Date());
			tbPoundRecords.setStatus(OwnGlobals.DATA_STAUS_1);
			
			int effectNum = tbPoundRecordsDao.updateNotNull(tbPoundRecords);
			
			if(effectNum > 1) {
				result2 = true;
			}
		}
		
		return result1&&result2;
	}

	@Override
	public Map<String, String> getPurchaseByPurchaseId(String purchaseId) {
		
		return dispatchingInfoDao.getPurchaseByPurchaseId(purchaseId);
	}

	@Override
	public Map<String, String> getPurchaseByCarriageId(String carriageId) {

		return dispatchingInfoDao.getPurchaseByCarriageId(carriageId);
	}

	@Override
	public Execution<Map<String, String>> getPurchaseByLicensePlate(String licensePlate) {
		if(StringUtils.isEmpty(licensePlate)) {
			return new Execution<Map<String,String>>(StateEnum.EMPTY);
		}
		
		List<Map<String,String>> list = dispatchingInfoDao.getPurchaseByLicensePlate(licensePlate);
		if(ObjectUtils.isEmpty(list)) {
			return new Execution<Map<String,String>>(StateEnum.EMPTY);
		}
		
		return new Execution<>(StateEnum.SUCCESS,list);
		
	}

	
 	
}
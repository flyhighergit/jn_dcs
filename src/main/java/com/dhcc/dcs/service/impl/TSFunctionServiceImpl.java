package com.dhcc.dcs.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhcc.dcs.dao.TSFunctionDao;
import com.dhcc.dcs.entity.TSFunction;
import com.dhcc.dcs.service.TSFunctionService;

@Service
public class TSFunctionServiceImpl implements TSFunctionService {

	@Autowired
	private TSFunctionDao tSFunctionDao;

	
	/**
	 * 获取用户拥有的菜单 jlf
	 * @return
	 */
	public List<TSFunction> getTSFunctionByMap(Map map){
		
		return tSFunctionDao.getTSFunctionByMap(map);
	}

}

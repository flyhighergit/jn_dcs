package com.dhcc.dcs.service;

import java.util.List;
import java.util.Map;

import com.dhcc.dcs.entity.TSFunction;

public interface TSFunctionService {
	
	
	/**
	 * 获取用户拥有的菜单 jlf
	 * @return
	 */
	public List<TSFunction> getTSFunctionByMap(Map<String,String> map);
	
	
}

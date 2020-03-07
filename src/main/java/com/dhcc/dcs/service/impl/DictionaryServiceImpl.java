package com.dhcc.dcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dhcc.dcs.dao.DictionaryDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DictionaryService;


@Service
public class DictionaryServiceImpl implements DictionaryService{
	
	@Autowired
	private DictionaryDao DictionaryDao;
	
	@Override
	public Execution<List<Map<String,String>>> getByType(String type) {
		if(StringUtils.isEmpty(type)) {
			return new Execution<List<Map<String,String>>>(StateEnum.EMPTY);
		}
		
		List<Map<String,String>> list = DictionaryDao.getByType(type);
		
		if(list==null||list.isEmpty()) {
			
			return new Execution<List<Map<String,String>>>(StateEnum.EMPTY);
		}else {
			return new Execution<List<Map<String,String>>>(StateEnum.SUCCESS,list);
		}
	}

	@Override
	public Map<String, String> getByTypeAndCode(String type, String code) {
		// TODO Auto-generated method stub
		return DictionaryDao.getByTypeAndCode(type,code) ;
	}

}

package com.dhcc.dcs.service;

import java.util.List;
import java.util.Map;

import com.dhcc.dcs.dto.Execution;


public interface DictionaryService {

	Execution<List<Map<String, String>>> getByType(String type);
	Map<String, String> getByTypeAndCode(String type,String code);

}

package com.dhcc.dcs.dao;

import java.util.List;
import java.util.Map;

public interface DictionaryDao {

	List<Map<String, String>> getByType(String type);
	Map<String, String> getByTypeAndCode(String type, String code);
}

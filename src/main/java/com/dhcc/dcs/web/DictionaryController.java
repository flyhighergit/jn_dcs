package com.dhcc.dcs.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DictionaryService;

/**
 * 字典Controller
 * @author zhanglei
 *
 */
@RequestMapping("/dictionary")
@Controller
public class DictionaryController {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * 通过分组类型CODE获取字典数据
	 * @param typeCode 分组类型CODE
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/getByType")
	@ResponseBody
	public JSONObject getByType(String typeCode) {
		JSONObject json = new JSONObject();
		
		Execution<List<Map<String, String>>> execution = dictionaryService.getByType(typeCode);
		
		if(execution.getState()==StateEnum.SUCCESS.getState()) {
			json.put("success", true);
			json.put("result", execution.getT());
		}else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}
		
		return json;
	}
	
}

package com.dhcc.dcs.util;

import java.util.UUID;


/**
 * 项目参数工具类
 * 
 * @author King
 * 
 */
public class ResourceUtils {
	
	/**
	 * 获取32位UUID
	 * @return
	 */
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

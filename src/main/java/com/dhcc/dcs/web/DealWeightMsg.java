package com.dhcc.dcs.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.cache.CacheManager;
import com.dhcc.dcs.entity.TbQrCode;
import com.dhcc.dcs.service.TbQrCodeService;
import com.dhcc.dcs.util.HttpServletRequestUtil;

/**
 * 司机扫描二维码信息处理
 * @author zhanglei
 *
 */
@Controller
@RequestMapping("/dealMsg")
public class DealWeightMsg {
	
	@Autowired
	private TbQrCodeService tbQrCodeService;
	
	/**
	 * 设置数据
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/setdata")
	@ResponseBody
	public JSONObject setData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		
		String key = HttpServletRequestUtil.getString(request, "key");
		String value = HttpServletRequestUtil.getString(request, "value");
		
		if(StringUtils.isEmpty(key)||StringUtils.isEmpty(value)) {
			json.put("success", false);
			json.put("errMsg", "信息上传失败！");
			return json;
		}else {
			CacheManager.setData(key, value, 0);
			json.put("success", true);
		}
		
		return json;
	}
	
	/**
	 * 获取缓存数据（key:企业编码）
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/getdata")
	@ResponseBody
	public JSONObject getData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		
		String key = HttpServletRequestUtil.getString(request, "key");
		
		String value = CacheManager.getData(key);
		
		if(StringUtils.isEmpty(value)) {
			json.put("success", false);
		}else {
			json.put("success", true);
			json.put("value", value);
		}
		
		return json;
	}
	
	/**
	 * 清除缓存
	 * @param request
	 * @author zhanglei
	 */
	@RequestMapping("/clearCache")
	@ResponseBody
	public String clearCache(HttpServletRequest request) {
		
		String key = HttpServletRequestUtil.getString(request, "key");
		
		CacheManager.clear(key);
		
		return "success";
		
	}
	
	/**
	 * 检查缓存是否存在
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/checkCache")
	@ResponseBody
	public JSONObject checkCache(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		// 二维码存储的key值
		String key = HttpServletRequestUtil.getString(request, "key");
		
		// 根据key值查找二维码记录表对应的记录
		TbQrCode tbQrCode = tbQrCodeService.getByKey(key);
		Date nowTime = new Date();
		if(tbQrCode == null) {
			json.put("success", false);
			json.put("errMsg", "请扫描正确的二维码！");
			return json;
		}
		// 如果失效时间早于现在的时间，说明这个二维码已经过期
		if(tbQrCode.getEndTime().compareTo(nowTime) < 0) {
			json.put("success", false);
			json.put("errMsg", "二维码已失效！");
			return json;
		}
		// 如果生效时间晚于现在的时间，说明这个二维码还未生效
		if(tbQrCode.getBeginTime().compareTo(nowTime) > 0) {
			json.put("success", false);
			json.put("errMsg", "二维码还未激活！");
			return json;
		}
		
		// 获取到真实的key，这个key值由公司编码和磅房编码一起构成，确定唯一磅房
		String realKey = tbQrCode.getCompanyCode() + tbQrCode.getBfCode();
		
		// 如果缓存中该磅房已经存在，那么就提示需要排队，否则返回真实的key值
		if(CacheManager.check(realKey)) {
			json.put("success", false);
			json.put("errMsg", "正在排队，请稍后再扫描二维码");
		}else {
			json.put("realKey", realKey);
			json.put("success", true);
		}
		
		return json;
	}
}

package com.dhcc.dcs.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.entity.TbDriverSfz;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbDriverSfzService;
import com.dhcc.dcs.util.HttpServletRequestUtil;

/**
 * 司机身份证附件Controller
 * @author zhanglei
 *
 */
@Controller
@RequestMapping("/tbDriverInfoSfz")
public class TbDriverInfoSfzController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 支持上传商品详情图的最大数量
	private static final int IMAGEMAXCOUNT = 2;
	
	@Resource
    private TbDriverSfzService tbDriverSfzService;

	/**
	 * 上传合同图片
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upload(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		TSBaseUser userInfo = (TSBaseUser) request.getSession().getAttribute("user");
		if(userInfo==null) {
			json.put("success", false);
			json.put("errMsg", "当前会话已失效，请重新登录！");
			return json;
		}
		Boolean isEdit = HttpServletRequestUtil.getBoolean(request, "isEdit");
		
		List<ImageHolder> holderList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			if (commonsMultipartResolver.isMultipart(request)) {
				handleImage(request, holderList);
			} else {
				// 如果不是编辑模式，那么要求必须上传图片
				if(isEdit) {
					json.put("success", true);
				}else {
					json.put("success", false);
					json.put("errMsg", "图片上传不能为空！");
				}
				return json;
			}
		} catch (IOException e) {
			logger.error("保存司机身份证信息失败！error massage："+e.getMessage(),e);
			json.put("success", false);
			json.put("errMsg", "内部错误");
			return json;
		}
		
		// 如果图片文件列表不为空
		if(holderList.size()>0) {
			// 上传图片到服务器，并将图片信息保存至附件表中
			String driverInfoId = HttpServletRequestUtil.getString(request, "driverInfoId");
			Execution<TbDriverSfz> execution = tbDriverSfzService.upload(
					driverInfoId,userInfo.getId(),userInfo.getRealName(),holderList);
			
			// 结果处理
			if(execution.getState() == StateEnum.SUCCESS.getState()) {
				json.put("success", true);
			}else {
				json.put("success", false);
				json.put("errMsg", execution.getStateInfo());
			}
		}else {
			// 如果不是编辑模式，那么要求必须上传图片
			if(isEdit) {
				json.put("success", true);
			}else {
				json.put("success", false);
				json.put("errMsg", "图片上传不能为空！");
			}
			return json;
		}
		
		return json;
	}
	
	/**
	 * 单文件上传
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/uploadSingle")
	@ResponseBody
	public JSONObject uploadSingle(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		
		// 获取当前人信息
		TSBaseUser userInfo = (TSBaseUser) request.getSession().getAttribute("user");
		if(userInfo==null) {
			json.put("success", false);
			json.put("errMsg", "当前会话已失效，请重新登录！");
			return json;
		}
		
		// 读取图片
		ImageHolder holder = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			if (commonsMultipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile img = (CommonsMultipartFile) multipartRequest.getFile("img");
				if (img != null ) {
					holder = new ImageHolder(img.getOriginalFilename(), img.getInputStream());
				}
			} else {
				json.put("success", false);
				json.put("errMsg", "图片上传不能为空！");
				return json;
			}
		} catch (IOException e) {
			logger.error("保存司机身份证信息失败！error massage："+e.getMessage(),e);
			json.put("success", false);
			json.put("errMsg", "内部错误");
			return json;
		}
		
		// 上传图片到服务器
		String driverInfoId = HttpServletRequestUtil.getString(request, "driverInfoId");
		String oldImgId = HttpServletRequestUtil.getString(request, "oldImgId");
		// 创建图片记录信息
		TbDriverSfz tbDriverSfz = new TbDriverSfz();
		tbDriverSfz.setDriverId(driverInfoId);
		tbDriverSfz.setAttaName(holder.getImageName());
		tbDriverSfz.setUlUserId(userInfo.getId());
		tbDriverSfz.setUlUserName(userInfo.getUserName());
		tbDriverSfz.setUlTime(new Date());
		
		Execution<TbDriverSfz> execution = tbDriverSfzService.uploadSingle(tbDriverSfz,oldImgId,holder);
		
		// 结果处理
		if(execution.getState() == StateEnum.SUCCESS.getState()) {
			json.put("success", true);
			json.put("result", execution.getT());
		}else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}
		
		return json;
	}
	
	/**
	 * 将图片流处理成图片列表
	 * @param request
	 * @param contractImgList
	 * @return
	 * @throws IOException
	 * @author zhanglei
	 */
	private void handleImage(HttpServletRequest request, List<ImageHolder> holderList)
			throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		// 取出合同图片列表并构建List<ImageHolder>列表对象
		for(int i=0;i<IMAGEMAXCOUNT;i++) {
			CommonsMultipartFile img = (CommonsMultipartFile) multipartRequest.getFile("img"+i);
			if (img != null ) {
				ImageHolder imgholder = new ImageHolder(img.getOriginalFilename(), img.getInputStream());
				holderList.add(imgholder);
			} else {
				continue;
			}
		}
		
	}
	
	/**
	 * 根据附件ID删除附件及这个附件在附件表中的记录
	 * @param id
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/doDelete")
	@ResponseBody
	public JSONObject doDelete(String id) {
		JSONObject json = new JSONObject();
		
		try {
			tbDriverSfzService.remove(id);
			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			json.put("errMsg", "删除文件时发生异常！");
		}
		
		return json;
	}
}

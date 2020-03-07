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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dao.VehicleInfoDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.entity.TbAttachmentZgz;
import com.dhcc.dcs.entity.VehicleInfo;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbAttachmentZgzService;
import com.dhcc.dcs.service.VehicleInfoService;
import com.dhcc.dcs.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 资格证controller
 * 
 *
 */
@Controller
@RequestMapping("/tbAttachmentZgz")
public class TbAttachmentZgzController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 支持上传证件详情图的最大数量
	private static final int IMAGEMAXCOUNT = 10;
	@Resource
    private TbAttachmentZgzService tbAttachmentZgzService;
	@Resource
	private VehicleInfoDao vehicleInfoDao;
	@Resource
	private VehicleInfoService vehicleInfoService;
	
	 /**
	  *    新增资格证信息
     *  
     * @return
     */
    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doAddOld(HttpServletRequest request,String cId,String imgFlag) {
    	JSONObject json = new JSONObject();
    	TSBaseUser user = (TSBaseUser)request.getSession().getAttribute("user");
    	Boolean isEdit = HttpServletRequestUtil.getBoolean(request, "isEdit");
    	
    	
    	// 1.接收并转化相应的参数
   	    String vehicleInfoStr = HttpServletRequestUtil.getString(request,"vehicleInfoStr");
   	    ObjectMapper mapper = new ObjectMapper();
   	    VehicleInfo tbVehicleInfo1 = null;
		try {
			tbVehicleInfo1 = mapper.readValue(vehicleInfoStr, VehicleInfo.class);
		} catch (Exception e) {
			logger.error("对象转换失败！", e);
			json.put("success", false);
		    json.put("errorMsg", e.getMessage());
		    return json;
		}
		
		try {
			tbVehicleInfo1.setId(cId);
			vehicleInfoDao.updateNotNull(tbVehicleInfo1);
			 json.put("id", cId);
			 json.put("success", true);
		}
   	    catch (Exception e) {
   	         json.put("success", false);
   	         json.put("errMsg", e.toString());
   	         return json;
   	    }
    	
    	
    	// 2.接收图片信息
    	List<ImageHolder> imgList = new ArrayList<ImageHolder>();
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
    			request.getSession().getServletContext());
    	
    	try {
    		// 若请求中存在文件流，则取出相关的文件
    		if (multipartResolver.isMultipart(request)) {
    			imgList = handleImage(request, imgList);
    		}
    		else {
    			// 如果不是编辑模式，那么要求必须上传图片
    			if(isEdit) {
    				json.put("success", true);
    			}
    			else {
    				json.put("success", false);
        			json.put("errMsg", "请上传从业证图片");
    			}
    			
    			return json;
    		}
    	}
    	catch (Exception e) {
    		json.put("success", false);
    		json.put("errMsg", e.toString());
    		return json;
    	}
    	
    	
		// 3.添加附件信息
    	if (imgList.size() > 0) {
    		TbAttachmentZgz tbAttachmentZgz = new TbAttachmentZgz();
    		tbAttachmentZgz.setClId(cId);
    		tbAttachmentZgz.setUlUserId(user.getId());
    		tbAttachmentZgz.setUlUserName(user.getRealName());
    		try {
    			// 执行添加操作
    			Execution<TbAttachmentZgz> execution = tbAttachmentZgzService
    					.add(tbAttachmentZgz, imgList);
    			if (execution.getState() == StateEnum.SUCCESS.getState()) {
    				
    				VehicleInfo tbVehicleInfo = new VehicleInfo();
    				tbVehicleInfo.setId(cId);
    				if(isEdit) {
    					//更新修改时间、修改人ID、修改人
    					tbVehicleInfo.setUpdateTime(new Date());
    					tbVehicleInfo.setModifierId(user.getId());
    					tbVehicleInfo.setModifier(user.getRealName());
    					vehicleInfoDao.updateNotNull(tbVehicleInfo);
    				}
    				
    				json.put("success", true);
    				json.put("id", execution.getT().getId());
    			}
    			else {
    				json.put("success", false);
    				json.put("errMsg", execution.getStateInfo());
    			}
    		}
    		catch (Exception e) {
    			json.put("success", false);
    			json.put("errMsg", e.toString());
    			logger.error("添加资格证上传记录时出现异常！error message:"+e.getMessage(),e);
    			return json;
    		}
    	}
    	else {
    		VehicleInfo tbVehicleInfo = new VehicleInfo();
			tbVehicleInfo.setId(cId);
			if(isEdit) {
				//更新修改时间、修改人ID、修改人
				tbVehicleInfo.setUpdateTime(new Date());
				tbVehicleInfo.setModifierId(user.getId());
				tbVehicleInfo.setModifier(user.getRealName());
				vehicleInfoDao.updateNotNull(tbVehicleInfo);
				json.put("success", true);
			}
			else {
				json.put("success", false);
	    		json.put("errMsg", "请上传从业证图片");
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
			logger.error("保存从业证信息失败！error massage："+e.getMessage(),e);
			json.put("success", false);
			json.put("errMsg", "内部错误");
			return json;
		}
		
		// 上传图片到服务器
		String driverInfoId = HttpServletRequestUtil.getString(request, "driverInfoId");
		String oldImgId = HttpServletRequestUtil.getString(request, "oldImgId");
		// 创建图片记录信息
		TbAttachmentZgz tbAttachmentZgz = new TbAttachmentZgz();
		tbAttachmentZgz.setClId(driverInfoId);
		tbAttachmentZgz.setAttaName(holder.getImageName());
		tbAttachmentZgz.setUlUserId(userInfo.getId());
		tbAttachmentZgz.setUlUserName(userInfo.getRealName());
		tbAttachmentZgz.setUlTime(new Date());
		
		Execution<TbAttachmentZgz> execution = tbAttachmentZgzService.uploadSingle(tbAttachmentZgz,oldImgId,holder);
		
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
     * 图片预处理
     * 
     * @param request
     * @param thumbnail
     * @param imgList
     * @return
     * @throws IOException
     */
    private List<ImageHolder> handleImage(HttpServletRequest request,
            List<ImageHolder> imgList) throws IOException {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 取出详情图列表并构建List<ImageHolder>列表对象
        for (int i = 0; i <= IMAGEMAXCOUNT; i++) {
            CommonsMultipartFile imgFile = (CommonsMultipartFile) multipartRequest
                    .getFile("img" + i);
            if (imgFile != null) {
                // 若取出的第i个详情图片文件流不为空，则将其加入证件图列表
                String filename = imgFile.getOriginalFilename();
                ImageHolder img = new ImageHolder(filename,
                        imgFile.getInputStream());
                String extension = filename
                        .substring(filename.lastIndexOf("."));
                img.setImageName("img" + i);
                img.setExtension(extension);
                imgList.add(img);
            }
            else {
                // 若取出的第i个详情图片文件流为空，则进行下一次循环
                continue;
            }
        }
        return imgList;

    }
}

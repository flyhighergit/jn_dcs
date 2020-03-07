package com.dhcc.dcs.service;

import java.util.List;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbAttachmentXsz;


/**
 * 
 * 行驶证附件service接口
 * 
 */
public interface TbAttachmentXszService{

	Execution<TbAttachmentXsz> add(TbAttachmentXsz tbAttachmentXsz, List<ImageHolder> imgList);

	List<TbAttachmentXsz> getByCompanyCode(String vehicleInfoId);
	
	/**
	 * 单文件上传
	 * @param tbAttachmentZgz
	 * @param oldImgId
	 * @return
	 * @author 
	 * @param holder 
	 */
	Execution<TbAttachmentXsz> uploadSingle(TbAttachmentXsz tbAttachmentXsz, String oldImgId, ImageHolder holder);
	
}

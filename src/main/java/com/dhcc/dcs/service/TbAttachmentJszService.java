package com.dhcc.dcs.service;

import java.util.List;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbAttachmentJsz;


/**
 * 
 * 驾驶证附件service接口
 */
public interface TbAttachmentJszService{

	Execution<TbAttachmentJsz> add(TbAttachmentJsz tbAttachmentJsz, List<ImageHolder> imgList);

	List<TbAttachmentJsz> getByCompanyCode(String vehicleInfoId);
	
	/**
	 * 单文件上传
	 * @param tbAttachmentZgz
	 * @param oldImgId
	 * @return
	 * @author 
	 * @param holder 
	 */
	Execution<TbAttachmentJsz> uploadSingle(TbAttachmentJsz tbAttachmentJsz, String oldImgId, ImageHolder holder);
	
}

package com.dhcc.dcs.service;

import java.util.List;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbAttachmentZgz;


/**
 * 
 * 资格证service接口
 *
 */
public interface TbAttachmentZgzService{

	Execution<TbAttachmentZgz> add(TbAttachmentZgz tbAttachmentZgz, List<ImageHolder> imgList);

	List<TbAttachmentZgz> getByCompanyCode(String vehicleInfoId);
	
	/**
	 * 单文件上传
	 * @param tbAttachmentZgz
	 * @param oldImgId
	 * @return
	 * @author 
	 * @param holder 
	 */
	Execution<TbAttachmentZgz> uploadSingle(TbAttachmentZgz tbAttachmentZgz, String oldImgId, ImageHolder holder);
	
 	
}

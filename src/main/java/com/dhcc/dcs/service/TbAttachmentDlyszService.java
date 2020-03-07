package com.dhcc.dcs.service;

import java.util.List;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbAttachmentDlysz;


/**
 * 
 * 道路运输证（运营证）附件service接口
 *
 */
public interface TbAttachmentDlyszService{

	Execution<TbAttachmentDlysz> add(TbAttachmentDlysz tbAttachmentDlysz, List<ImageHolder> imgList);

	List<TbAttachmentDlysz> getByCompanyCode(String vehicleInfoId);
	
	/**
	 * 单文件上传
	 * @param tbAttachmentZgz
	 * @param oldImgId
	 * @return
	 * @author 
	 * @param holder 
	 */
	Execution<TbAttachmentDlysz> uploadSingle(TbAttachmentDlysz tbAttachmentDlysz, String oldImgId, ImageHolder holder);
	
 	
}

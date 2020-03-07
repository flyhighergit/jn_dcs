package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TbAttachmentJsz;


/**
 * 
 * 驾驶证附件dao接口.
 * 
 */
public interface TbAttachmentJszDao {

	int batchAdd(List<TbAttachmentJsz> list);

	List<TbAttachmentJsz> getByCompanyCode(String vehicleInfoId);

	int delete(String id);

	int getCount(String clId);
	
	int add(TbAttachmentJsz tbAttachmentJsz);
	
	TbAttachmentJsz getById(String id);

}
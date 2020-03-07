package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TbAttachmentXsz;


/**
 * 
 * 行驶证附件dao接口.
 *
 */
public interface TbAttachmentXszDao{

	int batchAdd(List<TbAttachmentXsz> list);

	List<TbAttachmentXsz> getByCompanyCode(String vehicleInfoId);

	int delete(String id);

	int getCount(String clId);
	
	int add(TbAttachmentXsz tbAttachmentXsz);
	
	TbAttachmentXsz getById(String id);

}
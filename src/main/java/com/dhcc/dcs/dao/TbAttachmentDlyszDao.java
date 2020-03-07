package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TbAttachmentDlysz;


/**
 * 
 * 道路运输证（运营证）附件dao接口.
 * 
 */
public interface TbAttachmentDlyszDao {

	int batchAdd(List<TbAttachmentDlysz> list);

	List<TbAttachmentDlysz> getByCompanyCode(String vehicleInfoId);

	int delete(String id);

	int getCount(String clId);
	
	int add(TbAttachmentDlysz tbAttachmentDlysz);
	
	TbAttachmentDlysz getById(String id);

}
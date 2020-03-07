package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TbAttachmentZgz;


/**
 * 
 * 资格证dao接口.
 * 
 */
public interface TbAttachmentZgzDao {

	int batchAdd(List<TbAttachmentZgz> list);

	List<TbAttachmentZgz> getByCompanyCode(String vehicleInfoId);

	int delete(String id);

	int getCount(String clId);
	
	int add(TbAttachmentZgz tbAttachmentZgz);
	
	TbAttachmentZgz getById(String id);


}
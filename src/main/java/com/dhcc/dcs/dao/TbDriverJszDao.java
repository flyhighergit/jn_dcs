package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TbDriverJsz;


public interface TbDriverJszDao {
	int batchAdd(List<TbDriverJsz> attachmentList);

	List<TbDriverJsz> findByDriverInfoId(String driverInfoId);

	void delete(String id);

	TbDriverJsz getById(String id);

	void deleteByDriverInfoId(String driverInfoId);

	int add(TbDriverJsz tbDriverJsz);
}

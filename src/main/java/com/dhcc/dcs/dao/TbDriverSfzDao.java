package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TbDriverSfz;


public interface TbDriverSfzDao {
	int batchAdd(List<TbDriverSfz> attachmentList);

	List<TbDriverSfz> findByDriverInfoId(String driverInfoId);

	void delete(String id);

	TbDriverSfz getById(String id);

	void deleteByDriverInfoId(String driverInfoId);

	int add(TbDriverSfz tbDriverSfz);
}

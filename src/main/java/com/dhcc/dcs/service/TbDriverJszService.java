package com.dhcc.dcs.service;

import java.util.List;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbDriverJsz;


public interface TbDriverJszService {
	/**
	 * 上传司机驾驶证并保存到附件表
	 * @param driverInfoId
	 * @param userId
	 * @param userName
	 * @param contractImgList
	 * @return
	 * @author zhanglei
	 */
	Execution<TbDriverJsz> upload(String driverInfoId,String userId,String userName,List<ImageHolder> contractImgList);
	
	/**
	 * 根据司机信息ID获取对应的附件记录
	 * @param driverInfoId
	 * @return
	 * @author zhanglei
	 */
	List<TbDriverJsz> getByDriverInfoId(String driverInfoId);
	
	/**
	 * 根据附件ID删除对应的附件文件以及这条记录
	 * @param id
	 * @return
	 * @author zhanglei
	 */
	void remove(String id);
	
	/**
	 * 根据司机信息ID，批量删除该司机下的附件及附件表中的记录
	 * @param driverInfoId
	 * @author zhanglei
	 */
	void batchRemove(String driverInfoId);

	Execution<TbDriverJsz> uploadSingle(TbDriverJsz tbDriverJsz, String oldImgId, ImageHolder holder);

}

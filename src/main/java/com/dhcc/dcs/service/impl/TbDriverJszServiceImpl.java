package com.dhcc.dcs.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dhcc.dcs.dao.TbDriverJszDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbDriverJsz;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbDriverJszService;
import com.dhcc.dcs.util.ImageUtil;
import com.dhcc.dcs.util.PathUtil;



@Service("tbDriverJszService")
public class TbDriverJszServiceImpl implements TbDriverJszService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TbDriverJszDao tbDriverJszDao;
	
	@Override
	public Execution<TbDriverJsz> upload(String driverInfoId, String userId, String userName,
			List<ImageHolder> contractImgList) {
		if(StringUtils.isEmpty(driverInfoId)) {
			return new Execution<>(StateEnum.EMPTY);
		}
		// 如果确实是有图片需要添加的，就执行批量添加操作
		List<TbDriverJsz> attachmentList = CreateAttachmentList(driverInfoId,userId,userName,contractImgList);
		if (attachmentList.size() > 0) {
			try {
				int effectedNum = tbDriverJszDao.batchAdd(attachmentList);
				if (effectedNum <= 0) {
					return new Execution<>(StateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				logger.error("添加司机驾驶证图片时发生异常，error massage："+e.getMessage(),e);
				return new Execution<>(StateEnum.INNER_ERROR);
			}
		}
		
		return new Execution<>(StateEnum.SUCCESS,attachmentList);
	}

	@Override
	public List<TbDriverJsz> getByDriverInfoId(String driverInfoId) {
		
		return tbDriverJszDao.findByDriverInfoId(driverInfoId);
	}

	@Override
	public void remove(String id) {
		// 根据id获取原来的图片
		TbDriverJsz tbDriverJsz = tbDriverJszDao.getById(id);
		// 删除掉原来的图片
		ImageUtil.deleteFileOrPath(tbDriverJsz.getAttaPath());
		// 删除数据库里原有图片的信息
		tbDriverJszDao.delete(id);
	}

	@Override
	public void batchRemove(String driverInfoId) {
		// 根据productId获取原来的图片
		List<TbDriverJsz> attachmentList = tbDriverJszDao.findByDriverInfoId(driverInfoId);
		// 删除掉原来的图片
		for (TbDriverJsz tbDriverJsz : attachmentList) {
			ImageUtil.deleteFileOrPath(tbDriverJsz.getAttaPath());
		}
		
		// 删除数据库里原有图片的信息
		tbDriverJszDao.deleteByDriverInfoId(driverInfoId);
	}
	
	private List<TbDriverJsz> CreateAttachmentList(String driverInfoId,String userId,String userName,List<ImageHolder> contractImgHolderList) {
		// 获取图片存储路径，这里直接存放到相应合同的文件夹底下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String dest = PathUtil.getJszPath(driverInfoId, date);
		
		// 创建需要批量操作的合同附件实体类列表
		List<TbDriverJsz> attachmentList = new ArrayList<>();
		
		// 遍历图片依次上传到服务器，并添加进img实体类里
		for (ImageHolder contractImg : contractImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(contractImg, dest);
			TbDriverJsz tbDriverJsz = new TbDriverJsz();
			tbDriverJsz.setId(UUID.randomUUID().toString().replace("-", ""));
			tbDriverJsz.setDriverId(driverInfoId);
			tbDriverJsz.setAttaPath(imgAddr);
			tbDriverJsz.setAttaName(contractImg.getImageName());
			tbDriverJsz.setUlTime(new Date());
			tbDriverJsz.setUlUserId(userId);
			tbDriverJsz.setUlUserName(userName);
			attachmentList.add(tbDriverJsz);
		}
		
		return attachmentList;
	}

	@Override
	@Transactional
	public Execution<TbDriverJsz> uploadSingle(TbDriverJsz tbDriverJsz, String oldImgId, ImageHolder holder) {
		// 获取图片存储路径，这里直接存放到相应合同的文件夹底下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String dest = PathUtil.getJszPath(tbDriverJsz.getDriverId(), date);
		
		try {
			// 将图片保存到服务器
			String imgAddr = ImageUtil.generateNormalImg(holder, dest);
			tbDriverJsz.setAttaPath(imgAddr);
			tbDriverJsz.setId(UUID.randomUUID().toString().replace("-", ""));
			
			// 将新添加的图片添加到附件表
			int effectedNum = tbDriverJszDao.add(tbDriverJsz);
			if (effectedNum <= 0) {
			    logger.error("新增司机身份证图片信息时，返回0条变更");
			    return new Execution<TbDriverJsz>(StateEnum.INNER_ERROR);
			}else {
				// 如果有旧图片ID，旧把旧图片删除
				TbDriverJsz oldImg = tbDriverJszDao.getById(oldImgId);
				if(oldImg!=null) {
					// 删除文件
					ImageUtil.deleteFileOrPath(oldImg.getAttaPath());
					// 删除替换的图片记录
					tbDriverJszDao.delete(oldImgId);
				}
				return new Execution<TbDriverJsz>(StateEnum.SUCCESS,tbDriverJsz);
			}
		} catch (Exception e) {
			logger.error("上传图片失败！error message："+e.getMessage(),e);
			return new Execution<TbDriverJsz>(StateEnum.INNER_ERROR);
		}
	}

}

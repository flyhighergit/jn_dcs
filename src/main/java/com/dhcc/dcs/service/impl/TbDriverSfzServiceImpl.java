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

import com.dhcc.dcs.dao.TbDriverSfzDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbDriverSfz;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbDriverSfzService;
import com.dhcc.dcs.util.ImageUtil;
import com.dhcc.dcs.util.PathUtil;



@Service("tbDriverSfzService")
public class TbDriverSfzServiceImpl implements TbDriverSfzService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TbDriverSfzDao tbDriverSfzDao;

	@Override
	public Execution<TbDriverSfz> upload(String driverInfoId, String userId, String userName,
			List<ImageHolder> holderList) {
		
		if(StringUtils.isEmpty(driverInfoId)) {
			return new Execution<>(StateEnum.EMPTY);
		}
		// 如果确实是有图片需要添加的，就执行批量添加操作
		List<TbDriverSfz> attachmentList = CreateAttachmentList(driverInfoId,userId,userName,holderList);
		if (attachmentList.size() > 0) {
			try {
				int effectedNum = tbDriverSfzDao.batchAdd(attachmentList);
				if (effectedNum <= 0) {
					return new Execution<>(StateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				logger.error("添加司机身份证图片时发生异常，error massage："+e.getMessage(),e);
				return new Execution<>(StateEnum.INNER_ERROR);
			}
		}
		
		return new Execution<>(StateEnum.SUCCESS,attachmentList);
	}

	@Override
	public List<TbDriverSfz> getByDriverInfoId(String driverInfoId) {
		
		return tbDriverSfzDao.findByDriverInfoId(driverInfoId);
	}

	@Override
	public void remove(String id) {
		// 根据id获取原来的图片
		TbDriverSfz tbDriverSfz = tbDriverSfzDao.getById(id);
		// 删除掉原来的图片
		ImageUtil.deleteFileOrPath(tbDriverSfz.getAttaPath());
		// 删除数据库里原有图片的信息
		tbDriverSfzDao.delete(id);
	}

	@Override
	public void batchRemove(String driverInfoId) {
		// 根据productId获取原来的图片
		List<TbDriverSfz> attachmentList = tbDriverSfzDao.findByDriverInfoId(driverInfoId);
		// 删除掉原来的图片
		for (TbDriverSfz tbDriverSfz : attachmentList) {
			ImageUtil.deleteFileOrPath(tbDriverSfz.getAttaPath());
		}
		
		// 删除数据库里原有图片的信息
		tbDriverSfzDao.deleteByDriverInfoId(driverInfoId);
	}
	
	private List<TbDriverSfz> CreateAttachmentList(String driverInfoId,String userId,String userName,List<ImageHolder> contractImgHolderList) {
		// 获取图片存储路径，这里直接存放到相应合同的文件夹底下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String dest = PathUtil.getSfzPath(driverInfoId, date);
		
		// 创建需要批量操作的合同附件实体类列表
		List<TbDriverSfz> attachmentList = new ArrayList<>();
		
		// 遍历图片依次上传到服务器，并添加进img实体类里
		for (ImageHolder contractImg : contractImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(contractImg, dest);
			TbDriverSfz tbDriverSfz = new TbDriverSfz();
			tbDriverSfz.setId(UUID.randomUUID().toString().replace("-", ""));
			tbDriverSfz.setDriverId(driverInfoId);
			tbDriverSfz.setAttaPath(imgAddr);
			tbDriverSfz.setAttaName(contractImg.getImageName());
			tbDriverSfz.setUlTime(new Date());
			tbDriverSfz.setUlUserId(userId);
			tbDriverSfz.setUlUserName(userName);
			attachmentList.add(tbDriverSfz);
		}
		
		return attachmentList;
	}

	@Override
	@Transactional
	public Execution<TbDriverSfz> uploadSingle(TbDriverSfz tbDriverSfz, String oldImgId, ImageHolder holder) {
		// 获取图片存储路径，这里直接存放到相应合同的文件夹底下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String dest = PathUtil.getSfzPath(tbDriverSfz.getDriverId(), date);
		
		try {
			// 将图片保存到服务器
			String imgAddr = ImageUtil.generateNormalImg(holder, dest);
			tbDriverSfz.setAttaPath(imgAddr);
			tbDriverSfz.setId(UUID.randomUUID().toString().replace("-", ""));
			
			
			// 将新添加的图片添加到附件表
			int effectedNum = tbDriverSfzDao.add(tbDriverSfz);
			if (effectedNum <= 0) {
			    logger.error("新增司机身份证图片信息时，返回0条变更");
			    return new Execution<TbDriverSfz>(StateEnum.INNER_ERROR);
			}else {
				// 如果有旧图片ID，旧把旧图片删除
				TbDriverSfz oldImg = tbDriverSfzDao.getById(oldImgId);
				if(oldImg!=null) {
					// 删除文件
					ImageUtil.deleteFileOrPath(oldImg.getAttaPath());
					// 删除替换的图片记录
					tbDriverSfzDao.delete(oldImgId);
				}
				return new Execution<TbDriverSfz>(StateEnum.SUCCESS,tbDriverSfz);
			}
		} catch (Exception e) {
			logger.error("上传图片失败！error message："+e.getMessage(),e);
			return new Execution<TbDriverSfz>(StateEnum.INNER_ERROR);
		}
	}
	
	@Override
	@Transactional
	public Execution<TbDriverSfz> add(TbDriverSfz tbDriverSfz) {
		try {
			tbDriverSfz.setId(UUID.randomUUID().toString().replace("-", ""));
			tbDriverSfz.setUlTime(new Date());
			
            int effectedNum = tbDriverSfzDao.add(tbDriverSfz);
            if (effectedNum <= 0) {
                logger.error("新增司机身份证图片信息时，返回0条变更");
                return new Execution<TbDriverSfz>(StateEnum.INNER_ERROR);
            }
        } catch (Exception e) {
            logger.error("新增司机身份证图片信息失败！error message:" + e.getMessage());
            return new Execution<TbDriverSfz>(StateEnum.INNER_ERROR);
        }
		
        return new Execution<TbDriverSfz>(StateEnum.DRAFT, tbDriverSfz);
	}
	
}

package com.dhcc.dcs.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.dcs.dao.TbAttachmentZgzDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbAttachmentZgz;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbAttachmentZgzService;
import com.dhcc.dcs.util.ImageUtil;
import com.dhcc.dcs.util.PathUtil;


/**
 * 
 * 资格证service实现类
 * 
 */
@Service("tbAttachmentZgzService")
public class TbAttachmentZgzServiceImpl implements TbAttachmentZgzService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TbAttachmentZgzDao tbAttachmentZgzDao;

	@Override
	public Execution<TbAttachmentZgz> add(TbAttachmentZgz tbAttachmentZgz, List<ImageHolder> imgList) {
		// 空值判断
		if (imgList != null) {
			
			// 若附件不为空则添加
			if (imgList != null && imgList.size() > 0) {
				try {
					addImgList(tbAttachmentZgz, imgList);
				} catch (Exception e) {
					logger.error("资格证相关图片上传失败！"+e.getMessage(),e);
					return new Execution<TbAttachmentZgz>(StateEnum.INNER_ERROR);
				}
			}
			return new Execution<TbAttachmentZgz>(StateEnum.SUCCESS, tbAttachmentZgz);
		} else {
			// 传参为空则返回空值错误信息
			return new Execution<TbAttachmentZgz>(StateEnum.EMPTY);
		}
	}
	
	/**
	 * 批量添加图片
	 * @param vehicleInfo
	 * @param imgHolderList
	 * @throws Exception
	 */
	private void addImgList(TbAttachmentZgz tbAttachmentZgz, List<ImageHolder> imgHolderList) throws Exception {
		
		List<TbAttachmentZgz> list = new ArrayList<>();
		
		// 获取图片存储路径，这里直接存放到相应资格证的文件夹下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String clId = tbAttachmentZgz.getClId();
		String ulUserId = tbAttachmentZgz.getUlUserId();
		String ulUserName = tbAttachmentZgz.getUlUserName();
		
		// 遍历图片，并添加进资格证信息实体类里
		for (ImageHolder imgHolder : imgHolderList) {
			tbAttachmentZgz = new TbAttachmentZgz();
			tbAttachmentZgz.setId(UUID.randomUUID().toString().replace("-", ""));
			tbAttachmentZgz.setClId(clId);
    		tbAttachmentZgz.setUlUserId(ulUserId);
    		tbAttachmentZgz.setUlUserName(ulUserName);
    		tbAttachmentZgz.setUlTime(new Date());
			String dest = PathUtil.getZgzPath(tbAttachmentZgz.getId(),date);
			String imgAddr = ImageUtil.generateThumbnail(imgHolder, dest);
			tbAttachmentZgz.setAttaPath(imgAddr);
			tbAttachmentZgz.setAttaName(imgHolder.getImageName());
			list.add(tbAttachmentZgz);
		}
		
		// 添加图片路径到数据库
		try {
			// 更新  先删除在插入   
			int count = tbAttachmentZgzDao.getCount(clId);
			if(count > 0) {
				tbAttachmentZgzDao.delete(clId);
			}
			int effectedNum = tbAttachmentZgzDao.batchAdd(list);
			if (effectedNum <= 0) {
				throw new Exception("添加证件图片失败");
			}
		} catch (Exception e) {
			throw new Exception("添加证件图片失败:" + e.toString());
		}
		
	}
	
	
	@Override
	@Transactional
	public Execution<TbAttachmentZgz> uploadSingle(TbAttachmentZgz tbAttachmentZgz, String oldImgId, ImageHolder holder) {
		// 获取图片存储路径，这里直接存放到相应合同的文件夹底下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String dest = PathUtil.getZgzPath(tbAttachmentZgz.getClId(), date);
		
		try {
			// 将图片保存到服务器
			String imgAddr = ImageUtil.generateNormalImg(holder, dest);
			tbAttachmentZgz.setAttaPath(imgAddr);
			tbAttachmentZgz.setId(UUID.randomUUID().toString().replace("-", ""));
			
			// 将新添加的图片添加到附件表
			int effectedNum = tbAttachmentZgzDao.add(tbAttachmentZgz);
			if (effectedNum <= 0) {
			    logger.error("新增从业证图片信息时，返回0条变更");
			    return new Execution<TbAttachmentZgz>(StateEnum.INNER_ERROR);
			}else {
				// 如果有旧图片ID，旧把旧图片删除
				TbAttachmentZgz oldImg = tbAttachmentZgzDao.getById(oldImgId);
				if(oldImg!=null) {
					// 删除文件
					ImageUtil.deleteFileOrPath(oldImg.getAttaPath());
					// 删除替换的图片记录
					tbAttachmentZgzDao.delete(oldImgId);
				}
				return new Execution<TbAttachmentZgz>(StateEnum.SUCCESS,tbAttachmentZgz);
			}
		} catch (Exception e) {
			logger.error("上传图片失败！error message："+e.getMessage(),e);
			return new Execution<TbAttachmentZgz>(StateEnum.INNER_ERROR);
		}
	}
	

	@Override
	public List<TbAttachmentZgz> getByCompanyCode(String vehicleInfoId) {
		
		return tbAttachmentZgzDao.getByCompanyCode(vehicleInfoId);
	}
}
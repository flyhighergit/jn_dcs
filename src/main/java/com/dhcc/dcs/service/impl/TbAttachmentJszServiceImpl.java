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

import com.dhcc.dcs.dao.TbAttachmentJszDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbAttachmentJsz;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbAttachmentJszService;
import com.dhcc.dcs.util.ImageUtil;
import com.dhcc.dcs.util.PathUtil;


/**
 * 
 * 驾驶证附件service实现类
 * 
 */
@Service("tbAttachmentJszService")
public class TbAttachmentJszServiceImpl implements TbAttachmentJszService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TbAttachmentJszDao tbAttachmentJszDao;

	@Override
	public Execution<TbAttachmentJsz> add(TbAttachmentJsz tbAttachmentJsz, List<ImageHolder> imgList) {
		// 空值判断
		if (imgList != null) {
			
			// 若附件不为空则添加
			if (imgList != null && imgList.size() > 0) {
				try {
					addImgList(tbAttachmentJsz, imgList);
				} catch (Exception e) {
					logger.error("资格证相关图片上传失败！"+e.getMessage(),e);
					return new Execution<TbAttachmentJsz>(StateEnum.INNER_ERROR);
				}
			}
			return new Execution<TbAttachmentJsz>(StateEnum.SUCCESS, tbAttachmentJsz);
		} else {
			// 传参为空则返回空值错误信息
			return new Execution<TbAttachmentJsz>(StateEnum.EMPTY);
		}
	}
	
	/**
	 * 批量添加图片
	 * @param vehicleInfo
	 * @param imgHolderList
	 * @throws Exception
	 */
	private void addImgList(TbAttachmentJsz tbAttachmentJsz, List<ImageHolder> imgHolderList) throws Exception {
		
		List<TbAttachmentJsz> list = new ArrayList<>();
		
		// 获取图片存储路径，这里直接存放到相应资格证的文件夹下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String clId = tbAttachmentJsz.getClId();
		String ulUserId = tbAttachmentJsz.getUlUserId();
		String ulUserName = tbAttachmentJsz.getUlUserName();
		
		// 遍历图片，并添加进资格证信息实体类里
		for (ImageHolder imgHolder : imgHolderList) {
			tbAttachmentJsz = new TbAttachmentJsz();
			tbAttachmentJsz.setId(UUID.randomUUID().toString().replace("-", ""));
			tbAttachmentJsz.setClId(clId);
			tbAttachmentJsz.setUlUserId(ulUserId);
			tbAttachmentJsz.setUlUserName(ulUserName);
			tbAttachmentJsz.setUlTime(new Date());
			String dest = PathUtil.getJszPath(tbAttachmentJsz.getId(),date);
			String imgAddr = ImageUtil.generateThumbnail(imgHolder, dest);
			tbAttachmentJsz.setAttaPath(imgAddr);
			tbAttachmentJsz.setAttaName(imgHolder.getImageName());
			list.add(tbAttachmentJsz);
		}
		
		// 添加图片路径到数据库
		try {
			// 更新  先删除在插入   
			int count = tbAttachmentJszDao.getCount(clId);
			if(count > 0) {
				tbAttachmentJszDao.delete(clId);
			}
			int effectedNum = tbAttachmentJszDao.batchAdd(list);
			if (effectedNum <= 0) {
				throw new Exception("添加证件图片失败");
			}
		} catch (Exception e) {
			throw new Exception("添加证件图片失败:" + e.toString());
		}
		
	}
	
	@Override
	@Transactional
	public Execution<TbAttachmentJsz> uploadSingle(TbAttachmentJsz tbAttachmentJsz, String oldImgId, ImageHolder holder) {
		// 获取图片存储路径，这里直接存放到相应合同的文件夹底下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String dest = PathUtil.getJszPath(tbAttachmentJsz.getClId(), date);
		
		try {
			// 将图片保存到服务器
			String imgAddr = ImageUtil.generateNormalImg(holder, dest);
			tbAttachmentJsz.setAttaPath(imgAddr);
			tbAttachmentJsz.setId(UUID.randomUUID().toString().replace("-", ""));
			
			// 将新添加的图片添加到附件表
			int effectedNum = tbAttachmentJszDao.add(tbAttachmentJsz);
			if (effectedNum <= 0) {
			    logger.error("新增驾驶证图片信息时，返回0条变更");
			    return new Execution<TbAttachmentJsz>(StateEnum.INNER_ERROR);
			}else {
				// 如果有旧图片ID，旧把旧图片删除
				TbAttachmentJsz oldImg = tbAttachmentJszDao.getById(oldImgId);
				if(oldImg!=null) {
					// 删除文件
					ImageUtil.deleteFileOrPath(oldImg.getAttaPath());
					// 删除替换的图片记录
					tbAttachmentJszDao.delete(oldImgId);
				}
				return new Execution<TbAttachmentJsz>(StateEnum.SUCCESS,tbAttachmentJsz);
			}
		} catch (Exception e) {
			logger.error("上传图片失败！error message："+e.getMessage(),e);
			return new Execution<TbAttachmentJsz>(StateEnum.INNER_ERROR);
		}
	}

	@Override
	public List<TbAttachmentJsz> getByCompanyCode(String vehicleInfoId) {
		
		return tbAttachmentJszDao.getByCompanyCode(vehicleInfoId);
	}
 	
}
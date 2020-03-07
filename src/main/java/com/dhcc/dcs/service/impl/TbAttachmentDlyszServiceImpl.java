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

import com.dhcc.dcs.dao.TbAttachmentDlyszDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TbAttachmentDlysz;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbAttachmentDlyszService;
import com.dhcc.dcs.util.ImageUtil;
import com.dhcc.dcs.util.PathUtil;


/**
 * 
 * 道路运输证（运营证）附件service实现类
 * 
 */
@Service("tbAttachmentDlyszService")
public class TbAttachmentDlyszServiceImpl implements TbAttachmentDlyszService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TbAttachmentDlyszDao tbAttachmentDlyszDao;

	@Override
	public Execution<TbAttachmentDlysz> add(TbAttachmentDlysz tbAttachmentDlysz, List<ImageHolder> imgList) {
		// 空值判断
		if (imgList != null) {
			
			// 若附件不为空则添加
			if (imgList != null && imgList.size() > 0) {
				try {
					addImgList(tbAttachmentDlysz, imgList);
				} catch (Exception e) {
					logger.error("资格证相关图片上传失败！"+e.getMessage(),e);
					return new Execution<TbAttachmentDlysz>(StateEnum.INNER_ERROR);
				}
			}
			return new Execution<TbAttachmentDlysz>(StateEnum.SUCCESS, tbAttachmentDlysz);
		} else {
			// 传参为空则返回空值错误信息
			return new Execution<TbAttachmentDlysz>(StateEnum.EMPTY);
		}
	}
	
	/**
	 * 批量添加图片
	 * @param vehicleInfo
	 * @param imgHolderList
	 * @throws Exception
	 */
	private void addImgList(TbAttachmentDlysz tbAttachmentDlysz, List<ImageHolder> imgHolderList) throws Exception {
		
		List<TbAttachmentDlysz> list = new ArrayList<>();
		
		// 获取图片存储路径，这里直接存放到相应资格证的文件夹下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String clId = tbAttachmentDlysz.getClId();
		String ulUserId = tbAttachmentDlysz.getUlUserId();
		String ulUserName = tbAttachmentDlysz.getUlUserName();
		
		// 遍历图片，并添加进资格证信息实体类里
		for (ImageHolder imgHolder : imgHolderList) {
			tbAttachmentDlysz = new TbAttachmentDlysz();
			tbAttachmentDlysz.setId(UUID.randomUUID().toString().replace("-", ""));
			tbAttachmentDlysz.setClId(clId);
    		tbAttachmentDlysz.setUlUserId(ulUserId);
    		tbAttachmentDlysz.setUlUserName(ulUserName);
    		tbAttachmentDlysz.setUlTime(new Date());
			String dest = PathUtil.getDlyszPath(tbAttachmentDlysz.getId(),date);
			String imgAddr = ImageUtil.generateThumbnail(imgHolder, dest);
			tbAttachmentDlysz.setAttaPath(imgAddr);
			tbAttachmentDlysz.setAttaName(imgHolder.getImageName());
			list.add(tbAttachmentDlysz);
		}
		
		// 添加图片路径到数据库
		try {
			// 更新  先删除在插入   
			int count = tbAttachmentDlyszDao.getCount(clId);
			if(count > 0) {
				tbAttachmentDlyszDao.delete(clId);
			}
			int effectedNum = tbAttachmentDlyszDao.batchAdd(list);
			if (effectedNum <= 0) {
				throw new Exception("添加证件图片失败");
			}
		} catch (Exception e) {
			throw new Exception("添加证件图片失败:" + e.toString());
		}
		
	}
	
	@Override
	@Transactional
	public Execution<TbAttachmentDlysz> uploadSingle(TbAttachmentDlysz tbAttachmentDlysz, String oldImgId, ImageHolder holder) {
		// 获取图片存储路径，这里直接存放到相应合同的文件夹底下
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String dest = PathUtil.getDlyszPath(tbAttachmentDlysz.getClId(), date);
		
		try {
			// 将图片保存到服务器
			String imgAddr = ImageUtil.generateNormalImg(holder, dest);
			tbAttachmentDlysz.setAttaPath(imgAddr);
			tbAttachmentDlysz.setId(UUID.randomUUID().toString().replace("-", ""));
			
			// 将新添加的图片添加到附件表
			int effectedNum = tbAttachmentDlyszDao.add(tbAttachmentDlysz);
			if (effectedNum <= 0) {
			    logger.error("新增道路运输证图片信息时，返回0条变更");
			    return new Execution<TbAttachmentDlysz>(StateEnum.INNER_ERROR);
			}else {
				// 如果有旧图片ID，旧把旧图片删除
				TbAttachmentDlysz oldImg = tbAttachmentDlyszDao.getById(oldImgId);
				if(oldImg!=null) {
					// 删除文件
					ImageUtil.deleteFileOrPath(oldImg.getAttaPath());
					// 删除替换的图片记录
					tbAttachmentDlyszDao.delete(oldImgId);
				}
				return new Execution<TbAttachmentDlysz>(StateEnum.SUCCESS,tbAttachmentDlysz);
			}
		} catch (Exception e) {
			logger.error("上传图片失败！error message："+e.getMessage(),e);
			return new Execution<TbAttachmentDlysz>(StateEnum.INNER_ERROR);
		}
	}

	@Override
	public List<TbAttachmentDlysz> getByCompanyCode(String vehicleInfoId) {
		
		return tbAttachmentDlyszDao.getByCompanyCode(vehicleInfoId);
	}
}
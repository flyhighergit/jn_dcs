package com.dhcc.dcs.service.impl;
import com.dhcc.dcs.service.TbQrCodeService;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.util.PageCalculator;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dao.TbQrCodeDao;
import com.dhcc.dcs.entity.TbQrCode;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 
 * 二维码service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2019-11-07 zhanglei
 * 	新建文件
 * </pre>
 * 
 * @author 
 * <pre>
 * SD
 * 	zhanglei
 * PG
 *	zhanglei
 * UT
 *
 * MA
 * </pre>
 * @version $Rev$
 *
 * <p/> $Id$
 *
 */
@Service("tbQrCodeService")
public class TbQrCodeServiceImpl implements TbQrCodeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TbQrCodeDao tbQrCodeDao;
	
	@Override
	public List<TbQrCode> findBySearch(TbQrCode tbQrCode) {

		return tbQrCodeDao.findBySearch(tbQrCode);
	}
	
	@Override
	public TbQrCode getById(String id) {

		return tbQrCodeDao.getById(id);
	}

	@Override
	public Execution<TbQrCode> add(TbQrCode tbQrCode) {
		if(tbQrCode == null) {
			return new Execution<>(StateEnum.EMPTY);
		}
		
		tbQrCode.setId(UUID.randomUUID().toString().replace("-", ""));
		try {
			int effectNum = tbQrCodeDao.add(tbQrCode);
			if (effectNum <= 0) {
			    logger.error("新增二维码时，返回0条变更");
			    return new Execution<>(StateEnum.INNER_ERROR);
			}
		} catch (Exception e) {
			logger.error("新增二维码失败！error massage:"+e.getMessage(),e);
			return new Execution<>(StateEnum.INNER_ERROR);
		}
		return new Execution<>(StateEnum.SUCCESS,tbQrCode);
	}
	
	@Override
	public Execution<TbQrCode> update(TbQrCode tbQrCode) {
		if(tbQrCode == null || StringUtils.isEmpty(tbQrCode.getId()) ) {
			return new Execution<>(StateEnum.EMPTY);
		}
		
		try {
			int effectNum = tbQrCodeDao.updateNotNull(tbQrCode);
			if (effectNum <= 0) {
			    logger.error("修改二维码时，返回0条变更");
			    return new Execution<>(StateEnum.INNER_ERROR);
			}
		} catch (Exception e) {
			logger.error("修改二维码失败！error massage:"+e.getMessage(),e);
			return new Execution<>(StateEnum.INNER_ERROR);
		}
		return new Execution<>(StateEnum.SUCCESS,tbQrCode);
	}

	@Override
	public void delete(String id) {
		tbQrCodeDao.delete(id);
	}
	
	
	@Override
	public Execution<TbQrCode> findExpireSoonByPage(TbQrCode tbQrCode, Integer pageIndex, Integer pageSize) {
		try {
			// 页转行
			int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
			List<TbQrCode> list = tbQrCodeDao.findExpireSoonByPage(tbQrCode, rowIndex, pageSize);
			int count = tbQrCodeDao.queryCountByExpireSoon(tbQrCode);
			Execution<TbQrCode> execution = new Execution<>(StateEnum.SUCCESS,list);
			execution.setCount(count);
			return execution;
		} catch (Exception e) {
			logger.error("分页查询二维码列表失败！error massage:"+e.getMessage(), e);
			return new Execution<>(StateEnum.INNER_ERROR);
		}
	}
	
	@Override
	public Execution<TbQrCode> findExpireByPage(TbQrCode tbQrCode, Integer pageIndex, Integer pageSize) {
		try {
			// 页转行
			int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
			List<TbQrCode> list = tbQrCodeDao.findExpireByPage(tbQrCode, rowIndex, pageSize);
			int count = tbQrCodeDao.queryCountByExpire(tbQrCode);
			Execution<TbQrCode> execution = new Execution<>(StateEnum.SUCCESS,list);
			execution.setCount(count);
			
			return execution;
		} catch (Exception e) {
			logger.error("分页查询二维码列表失败！error massage:"+e.getMessage(), e);
			return new Execution<>(StateEnum.INNER_ERROR);
		}
		
	}

	@Override
	public TbQrCode getByKey(String key) {
		return tbQrCodeDao.getByKey(key);
	}

	@Override
	public Execution<TbQrCode> findByPage(TbQrCode tbQrCode,Integer pageIndex,Integer pageSize) {
		try {
			// 页转行
			int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
			List<TbQrCode> list = tbQrCodeDao.findByPage(tbQrCode, rowIndex, pageSize);
			int count = tbQrCodeDao.queryCount(tbQrCode);
			Execution<TbQrCode> execution = new Execution<>(StateEnum.SUCCESS,list);
			execution.setCount(count);
			
			return execution;
		} catch (Exception e) {
			logger.error("分页查询二维码列表失败！error massage:"+e.getMessage(), e);
			return new Execution<>(StateEnum.INNER_ERROR);
		}
	}

	@Override
	public int getExpireNumber(TbQrCode tbQrCode) {
		return tbQrCodeDao.getExpireNumber(tbQrCode);
	}
}
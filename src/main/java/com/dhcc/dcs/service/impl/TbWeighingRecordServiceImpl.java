package com.dhcc.dcs.service.impl;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.dcs.dao.TbWeighingRecordDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbWeighingRecord;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbWeighingRecordService;


/**
 * 
 * 磅秤记录表service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-12 lz
 * 	新建文件
 * </pre>
 * 
 * @author 
 * <pre>
 * SD
 * 	lz
 * PG
 *	lz
 * UT
 *
 * MA
 * </pre>
 * @version $Rev$
 *
 * <p/> $Id$
 *
 */
@Service("tbWeighingRecordService")
public class TbWeighingRecordServiceImpl implements TbWeighingRecordService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TbWeighingRecordDao tbWeighingRecordDao;

	@Override
	@Transactional
	public void add(TbWeighingRecord tbWeighingRecord) {
		tbWeighingRecordDao.add(tbWeighingRecord);
	}

	@Override
	public String getCompanyName(String code) {
		return tbWeighingRecordDao.getCompanyName(code);
	}
	@Override
	public String getBnagChengCode(String code) {
		return tbWeighingRecordDao.getBnagChengCode(code);
	}

	@Override
	public TbWeighingRecord getById(String id) {
		return tbWeighingRecordDao.getById(id);
	}

	@Override
	@Transactional
	public Execution<TbWeighingRecord> modify(TbWeighingRecord tbWeighingRecord) {
		if(tbWeighingRecord==null||"".equals(tbWeighingRecord.getId())) {
			return new Execution<TbWeighingRecord>(StateEnum.EMPTY);
		}
		
		int effectNum = tbWeighingRecordDao.updateNotNull(tbWeighingRecord);
		
		if(effectNum<1) {
			logger.error("修改称重记录表时，返回0条变更");
			return new Execution<TbWeighingRecord>(StateEnum.INNER_ERROR,tbWeighingRecord);
		}else {
			return new Execution<TbWeighingRecord>(StateEnum.SUCCESS,tbWeighingRecord);
		}
		
	}
	
 	
}
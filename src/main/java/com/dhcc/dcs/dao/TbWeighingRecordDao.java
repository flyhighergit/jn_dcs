package com.dhcc.dcs.dao;

import com.dhcc.dcs.entity.TbWeighingRecord;

/**
 * 
 * 磅秤记录表dao接口.
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
public interface TbWeighingRecordDao{

	public int add(TbWeighingRecord tbWeighingRecord);
	public String  getCompanyName(String code);
	public String  getBnagChengCode(String code);
	public TbWeighingRecord getById(String id);
	public int updateNotNull(TbWeighingRecord tbWeighingRecord);
}
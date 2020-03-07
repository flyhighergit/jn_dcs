package com.dhcc.dcs.service;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbWeighingRecord;


/**
 * 
 * 磅秤记录表service接口
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
public interface TbWeighingRecordService{
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-12 lz
	 * 	新增
	 * </pre>
	 * 
	 * @param tbWeighingRecord
	 */
	public void add(TbWeighingRecord tbWeighingRecord);
	public TbWeighingRecord getById(String id);
	public String  getCompanyName(String code);
	public String  getBnagChengCode(String code);
	
	/**
	 * 修改 
	 * @param tbWeighingRecord
	 * @return
	 */
	public Execution<TbWeighingRecord> modify(TbWeighingRecord tbWeighingRecord);
}

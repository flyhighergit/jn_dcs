package com.dhcc.dcs.dao;
import java.util.List;
import java.util.Map;

import com.dhcc.dcs.entity.TbPoundRecords;

/**
 * 
 * 称重记录表dao接口.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-10-04 zhanglei
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
public interface TbPoundRecordsDao {

	List<TbPoundRecords> find(TbPoundRecords tbPoundRecords);

	TbPoundRecords getById(String id);

	int add(TbPoundRecords tbPoundRecords);

	int updateNotNull(TbPoundRecords tbPoundRecords);

	int delete(String id);

	List<TbPoundRecords> findUnderWay(String licensePlate);

	List<Map<String, String>> findHistoryWithContractInfo(TbPoundRecords tbPoundRecords);

	List<Map<String, Object>> findByPage(Map<String, Object> map);

	int queryCount(Map<String, Object> map);

	Map<String,Object> getWayBill(String poundRecordId);


}
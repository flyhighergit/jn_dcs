package com.dhcc.dcs.service;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbPoundRecords;
import java.util.List;
import java.util.Map;

/**
 * 
 * 称重记录表service接口
 * 
 * <pre>
 * 	历史记录：
 * 	2019-10-04 zhanglei
 * 	新建文件
 * </pre>
 * 
 * @author
 * 
 *         <pre>
 * SD
 * 	zhanglei
 * PG
 *	zhanglei
 * UT
 *
 * MA
 *         </pre>
 * 
 * @version $Rev$
 *
 *          <p/>
 *          $Id$
 *
 */
public interface TbPoundRecordsService {

	/**
	 * 
	 * <pre>
	 * 	2019-10-04 zhanglei
	 * 	分页查询
	 * </pre>
	 * 
	 * @param tbPoundRecords
	 * @param page
	 * @return
	 */
	public List<TbPoundRecords> findBySearch(TbPoundRecords tbPoundRecords);

	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param TbPoundRecords
	 * @return
	 */
	public TbPoundRecords getById(String id);

	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	新增
	 * </pre>
	 * 
	 * @param tbPoundRecords
	 */
	public Execution<TbPoundRecords> add(TbPoundRecords tbPoundRecords);

	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	修改
	 * </pre>
	 * 
	 * @param tbPoundRecords
	 */
	public Execution<TbPoundRecords> update(TbPoundRecords tbPoundRecords);

	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	删除
	 * </pre>
	 * 
	 * @param id
	 */
	public Execution<TbPoundRecords> delete(String id);

	/**
	 * 查询未完成（正在进行的称重）记录
	 * 
	 * @param tbPoundRecords
	 * @return
	 */
	public Execution<TbPoundRecords> findUnderWay(String licensePlate);

	Execution<Map<String, String>> getHistory(TbPoundRecords tbPoundRecords);

	public Execution<Map<String, Object>> findByPage(Map<String, Object> map);

	public Map<String,Object> getWayBill(String poundRecordId);
}

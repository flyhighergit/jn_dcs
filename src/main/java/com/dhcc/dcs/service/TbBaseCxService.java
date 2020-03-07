package com.dhcc.dcs.service;
import java.util.List;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbBaseCx;

/**
 * 
 * 车型service接口
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-26 zhanglei
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
public interface TbBaseCxService{
	
 	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	分页查询
	 * </pre>
	 * 
	 * @param tbBaseCx
	 * @param page
	 * @return
	 */
	public Execution<TbBaseCx> findByPage(TbBaseCx tbBaseCx,Integer pageIndex,Integer pageSize);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	查询
	 * </pre>
	 * 
	 * @param tbBaseCx
	 * @return
	 */
	public List<TbBaseCx> findBySearch(TbBaseCx tbBaseCx);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param TbBaseCx
	 * @return
	 */
	public TbBaseCx getById(String id);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	新增
	 * </pre>
	 * 
	 * @param tbBaseCx
	 */
	public Execution<TbBaseCx> add(TbBaseCx tbBaseCx);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	修改
	 * </pre>
	 * 
	 * @param tbBaseCx
	 */
	public Execution<TbBaseCx> update(TbBaseCx tbBaseCx);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	删除
	 * </pre>
	 * 
	 * @param id
	 */
	public Execution<TbBaseCx> delete(String id);

}

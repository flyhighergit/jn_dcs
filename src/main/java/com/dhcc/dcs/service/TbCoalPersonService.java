package com.dhcc.dcs.service;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbCoalPerson;

import java.util.List;
/**
 * 
 * 涉煤单位人员关联表service接口
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-25 zhanglei
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
public interface TbCoalPersonService{
	
 	/**
	 * 
	 * <pre>
	 * 	2019-09-25 zhanglei
	 * 	分页查询
	 * </pre>
	 * 
	 * @param tbCoalPerson
	 * @param page
	 * @return
	 */
	public Execution<TbCoalPerson> findByPage(TbCoalPerson tbCoalPerson,Integer pageIndex,Integer pageSize);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-25 zhanglei
	 * 	查询
	 * </pre>
	 * 
	 * @param tbCoalPerson
	 * @return
	 */
	public List<TbCoalPerson> findBySearch(TbCoalPerson tbCoalPerson);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-25 zhanglei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param TbCoalPerson
	 * @return
	 */
	public TbCoalPerson getById(String id);
}

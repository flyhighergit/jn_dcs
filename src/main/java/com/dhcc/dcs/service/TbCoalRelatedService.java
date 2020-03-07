package com.dhcc.dcs.service;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbCoalRelated;
import java.util.List;

/**
 * 
 * 涉煤关联表service接口
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
public interface TbCoalRelatedService{
	
 	/**
	 * 
	 * <pre>
	 * 	2019-09-25 zhanglei
	 * 	分页查询
	 * </pre>
	 * 
	 * @param tbCoalRelated
	 * @param page
	 * @return
	 */
	public Execution<TbCoalRelated> findByPage(TbCoalRelated tbCoalRelated,Integer pageIndex,Integer pageSize);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-25 zhanglei
	 * 	查询
	 * </pre>
	 * 
	 * @param tbCoalRelated
	 * @return
	 */
	public List<TbCoalRelated> findBySearch(TbCoalRelated tbCoalRelated);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-25 zhanglei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param TbCoalRelated
	 * @return
	 */
	public TbCoalRelated getById(String id);

	public Execution<TbCoalRelated> find(TbCoalRelated tbCoalRelated);
}

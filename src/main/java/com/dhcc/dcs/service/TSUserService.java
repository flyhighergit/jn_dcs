package com.dhcc.dcs.service;
import java.util.List;

import com.dhcc.dcs.entity.TSUser;


/**
 * 
 * 晋能用户拓展表service接口
 * 
 * <pre>
 * 	历史记录：
 * 	2019-12-08 guofei
 * 	新建文件
 * </pre>
 * 
 * @author 
 * <pre>
 * SD
 * 	guofei
 * PG
 *	guofei
 * UT
 *
 * MA
 * </pre>
 * @version $Rev$
 *
 * <p/> $Id$
 *
 */
public interface TSUserService{
	
	
	/**
	 * 
	 * <pre>
	 * 	2019-12-08 guofei
	 * 	查询
	 * </pre>
	 * 
	 * @param tSUser
	 * @return
	 */
	public List<TSUser> findBySearch(TSUser tSUser);
	
	/**
	 * 
	 * <pre>
	 * 	2019-12-08 guofei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param TSUser
	 * @return
	 */
	public TSUser getById(String id);
	
	/**
	 * 
	 * <pre>
	 * 	2019-12-08 guofei
	 * 	新增
	 * </pre>
	 * 
	 * @param tSUser
	 */
	public void add(TSUser tSUser);
	
	/**
	 * 
	 * <pre>
	 * 	2019-12-08 guofei
	 * 	修改
	 * </pre>
	 * 
	 * @param tSUser
	 */
	public void update(TSUser tSUser);
	
	/**
	 * 
	 * <pre>
	 * 	2019-12-08 guofei
	 * 	删除
	 * </pre>
	 * 
	 * @param id
	 */
	public void delete(String id);
}

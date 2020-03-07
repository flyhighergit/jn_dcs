package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TSUser;

/**
 * 
 * 晋能用户拓展表dao接口.
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
public interface TSUserDao {
	
	public List<TSUser> find(TSUser tSUser);
	
	public TSUser getById(String id);
	
	public void add(TSUser tSUser);
	
	public void update(TSUser tSUser);
	
	public void delete(String id);

}
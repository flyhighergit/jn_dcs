package com.dhcc.dcs.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dhcc.dcs.entity.TbCoalRelated;

/**
 * 
 * 涉煤关联表dao接口.
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
public interface TbCoalRelatedDao {

	List<TbCoalRelated> find(@Param("tbCoalRelated")TbCoalRelated tbCoalRelated, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);
	
	List<TbCoalRelated> findByPage(@Param("tbCoalRelated")TbCoalRelated tbCoalRelated, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

	int queryCount(TbCoalRelated tbCoalRelated);

	TbCoalRelated getById(String id);

	int insert(TbCoalRelated tbCoalRelated);
	
	int add(TbCoalRelated tbCoalRelated);

	int updateNotNull(TbCoalRelated tbCoalRelated);

	List<TbCoalRelated> findBySearch(TbCoalRelated tbCoalRelated);
	
	List<TbCoalRelated> findMaxId(String parentCode);
	
	int delete(String id);

}
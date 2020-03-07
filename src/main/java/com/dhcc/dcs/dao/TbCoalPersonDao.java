package com.dhcc.dcs.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dhcc.dcs.entity.TbCoalPerson;

/**
 * 
 * 涉煤单位人员关联表dao接口.
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
public interface TbCoalPersonDao {

	List<TbCoalPerson> find(@Param("tbCoalPerson")TbCoalPerson tbCoalPerson, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

	int queryCount(TbCoalPerson tbCoalPerson);

	TbCoalPerson getById(String id);

	List<TbCoalPerson> findBySearch(TbCoalPerson tbCoalPerson);


}
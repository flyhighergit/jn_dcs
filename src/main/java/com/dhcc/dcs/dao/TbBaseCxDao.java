package com.dhcc.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dhcc.dcs.entity.TbBaseCx;


/**
 * 
 * 车型dao接口.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-26 zhanglei
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
public interface TbBaseCxDao {

    List<TbBaseCx> find(@Param("tbBaseCx") TbBaseCx tbBaseCx,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int queryCount(TbBaseCx tbBaseCx);

    List<TbBaseCx> findBySearch(TbBaseCx tbBaseCx);

    TbBaseCx getById(String id);

    int insert(TbBaseCx tbBaseCx);

    int updateNotNull(TbBaseCx tbBaseCx);

	List<TbBaseCx> find1(@Param("tbBaseCx")TbBaseCx tbBaseCx, 
			@Param("rowIndex")int rowIndex, @Param("pageSize")Integer pageSize);

}
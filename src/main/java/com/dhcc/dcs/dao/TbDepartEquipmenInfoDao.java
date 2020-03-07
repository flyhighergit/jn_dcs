package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TbDepartEquipmenInfo;

/**
 * 
 * 企业设备管理信息dao接口.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-22 lz
 * 	新建文件
 * </pre>
 * 
 * @author
 * 
 *         <pre>
 * SD
 * 	lz
 * PG
 *	lz
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
public interface TbDepartEquipmenInfoDao {
    List<TbDepartEquipmenInfo> find(TbDepartEquipmenInfo info);
}
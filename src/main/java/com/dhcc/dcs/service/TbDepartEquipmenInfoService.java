package com.dhcc.dcs.service;

import java.util.List;

import com.dhcc.dcs.entity.TbDepartEquipmenInfo;

/**
 * 
 * 企业设备管理信息service接口
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
public interface TbDepartEquipmenInfoService {

    /**
     * 
     * <pre>
     * 	2019-09-22 lz
     * 	查询
     * </pre>
     * 
     * @param tbDepartEquipmenInfo
     * @return
     */
    public List<TbDepartEquipmenInfo> findBySearch(
            TbDepartEquipmenInfo tbDepartEquipmenInfo);

}

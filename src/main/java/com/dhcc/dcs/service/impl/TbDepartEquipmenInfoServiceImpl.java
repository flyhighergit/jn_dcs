package com.dhcc.dcs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhcc.dcs.dao.TbDepartEquipmenInfoDao;
import com.dhcc.dcs.entity.TbDepartEquipmenInfo;
import com.dhcc.dcs.service.TbDepartEquipmenInfoService;

/**
 * 
 * 企业设备管理信息service实现类
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
@Service("tbDepartEquipmenInfoService")
public class TbDepartEquipmenInfoServiceImpl implements TbDepartEquipmenInfoService {

	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Resource
    private TbDepartEquipmenInfoDao tbDepartEquipmenInfoDao;

    @Override
    public List<TbDepartEquipmenInfo> findBySearch(
            TbDepartEquipmenInfo tbDepartEquipmenInfo) {

        return tbDepartEquipmenInfoDao.find(tbDepartEquipmenInfo);
    }

}
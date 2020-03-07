package com.dhcc.dcs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.dcs.dao.DriverInfoDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DriverInfo;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DriverInfoService;
import com.dhcc.dcs.util.OwnGlobals;
import com.dhcc.dcs.util.PageCalculator;


/**
 * 
 * 司机信息service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-19 zhanglei
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
@Service("driverInfoService")
public class DriverInfoServiceImpl implements DriverInfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DriverInfoDao driverInfoDao;

    @Override
    public Execution<DriverInfo> findByPage(DriverInfo driverInfo,
            Integer pageIndex, Integer pageSize) {
        try {
            // 页转行
            int rowIndex = PageCalculator.calculateRowIndex(pageIndex,
                    pageSize);
            // 根据查询条件查询合同列表信息
            List<DriverInfo> list = driverInfoDao.findByPage(driverInfo, rowIndex,pageSize);
            // 查询在当前条件下的记录总数
            int count = driverInfoDao.queryCount(driverInfo);
            Execution<DriverInfo> execution = new Execution<>(StateEnum.SUCCESS, list);
            execution.setCount(count);

            return execution;
        }
        catch (Exception e) {
            logger.error("分页查询司机信息列表失败！", e);
            return new Execution<DriverInfo>(StateEnum.INNER_ERROR, driverInfo);
        }
    }

    @Override
    public List<DriverInfo> findBySearch(DriverInfo driverInfo) {

        return driverInfoDao.findBySearch(driverInfo);
    }

    @Override
    public DriverInfo getById(String id) {
        return driverInfoDao.getById(id);
    }
    
    @Override
    public DriverInfo getByFounderId(String id) {
        return driverInfoDao.getByFounderId(id);
    }
    
    @Override
    public DriverInfo getByIdCord(String id) {
        return driverInfoDao.getByIdCord(id);
    }

    @Override
    @Transactional
    public Execution<DriverInfo> add(DriverInfo driverInfo) {
        try {
            driverInfo.setId(UUID.randomUUID().toString().replace("-", ""));
            driverInfo.setCreateTime(new Date());
            driverInfo.setCheckStatu(OwnGlobals.CHECK_STAUS_3);
            
            int effectedNum = driverInfoDao.insert(driverInfo);
            if (effectedNum <= 0) {
                logger.error("新增司机信息时，返回0条变更");
                return new Execution<DriverInfo>(StateEnum.INNER_ERROR,
                        driverInfo);
            }
        } catch (Exception e) {
            logger.error("新增司机信息失败！error message:" + e.getMessage());
            return new Execution<DriverInfo>(StateEnum.INNER_ERROR, driverInfo);
        }

        return new Execution<DriverInfo>(StateEnum.DRAFT, driverInfo);
    }

    @Override
    @Transactional
    public Execution<DriverInfo> modify(DriverInfo driverInfo) {
        if (null == driverInfo || null == driverInfo.getId()) {
            return new Execution<DriverInfo>(StateEnum.EMPTY);
        }

        try {
            int effectedNum = driverInfoDao.updateNotNull(driverInfo);
            if (effectedNum <= 0) {
                logger.error("修改司机信息时，返回0条变更");
                return new Execution<DriverInfo>(StateEnum.INNER_ERROR,
                        driverInfo);
            }
        }
        catch (Exception e) {
            logger.error("修改司机信息失败！error message:" + e.getMessage());
            return new Execution<DriverInfo>(StateEnum.INNER_ERROR, driverInfo);
        }

        return new Execution<DriverInfo>(StateEnum.SUCCESS, driverInfo);
    }

    @Override
    @Transactional
    public int delete(String id) {
        return driverInfoDao.delete(id);
    }
    
    @Override
    public int unbindDriverInfo(String id) {
        return driverInfoDao.unbindDriverInfo(id);
    }
    
    @Override
    public int bindDriverInfo(DriverInfo driverInfo) {
        return driverInfoDao.bindDriverInfo(driverInfo);
    }

}
package com.dhcc.dcs.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.dcs.dao.DepartDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TSDepart;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DepartService;
import com.dhcc.dcs.util.PageCalculator;

@Service("departService")
public class DepartServiceImpl implements DepartService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartDao departDao;

    @Override
    @Transactional
    public Execution<TSDepart> add(TSDepart depart, ImageHolder thumbnail) {
        if (null == depart) {
            return new Execution<TSDepart>(StateEnum.EMPTY);
        }

        /*try {
            int effectedNum = 0;
        }
        catch (Exception e) {
            logger.error("新增承运合同失败！error message:" + e.getMessage());
            return new Execution<TSDepart>(StateEnum.INNER_ERROR, depart);
        }*/

        return new Execution<TSDepart>(StateEnum.SUCCESS, depart);
    }

    @Override
    @Transactional
    public Execution<TSDepart> modify(TSDepart depart, ImageHolder thumbnail) {
        if (null == depart || null == depart.getId()) {
            return new Execution<TSDepart>(StateEnum.EMPTY);
        }

        try {
            int effectedNum = departDao.update(depart);
            if (effectedNum <= 0) {
                logger.error("修改返回0条变更");
                return new Execution<TSDepart>(StateEnum.INNER_ERROR, depart);
            }
            else {
            }
        }
        catch (Exception e) {
            logger.error("修改失败！error message:" + e.getMessage());
            return new Execution<TSDepart>(StateEnum.INNER_ERROR, depart);
        }

        return new Execution<TSDepart>(StateEnum.SUCCESS, depart);
    }

    @Override
    public TSDepart getById(String id) {

        return departDao.getById(id);
    }

    @Override
    public Execution<TSDepart> findByPage(TSDepart depart, Integer pageIndex,
            Integer pageSize) {
        try {
            // 页转行
            int rowIndex = PageCalculator.calculateRowIndex(pageIndex,
                    pageSize);
            // 根据查询条件查询合同列表信息
            List<TSDepart> list = departDao.find(depart, rowIndex, pageSize);
            // 查询在当前条件下的记录总数
            int count = departDao.queryCount(depart);
            Execution<TSDepart> execution = new Execution<>(StateEnum.SUCCESS,
                    list);
            execution.setList(list);
            execution.setCount(count);

            return execution;
        }
        catch (Exception e) {
            logger.error("分页查询列表失败！", e);
            return new Execution<TSDepart>(StateEnum.INNER_ERROR, depart);
        }

    }

    @Override
    public List<TSDepart> find(TSDepart depart) {

        return departDao.find(depart);
    }
}

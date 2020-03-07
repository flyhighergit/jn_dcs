package com.dhcc.dcs.service;

import java.util.List;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.dto.ImageHolder;
import com.dhcc.dcs.entity.TSDepart;

public interface DepartService {
    /**
     * 添加
     * 
     * @param TSDepart
     * @return
     */
    Execution<TSDepart> add(TSDepart depart, ImageHolder thumbnail);

    /**
     * 修改
     * 
     * @param TSDepart
     * @return
     */
    Execution<TSDepart> modify(TSDepart depart, ImageHolder thumbnail);

    /**
     * 根据主键获取
     * 
     * @param id
     * @return
     */
    TSDepart getById(String id);

    /**
     * 分页查询列表
     * 
     * @param carriage
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Execution<TSDepart> findByPage(TSDepart depart, Integer pageIndex,
            Integer pageSize);

    /**
     * 查询列表
     * 
     * @param depart
     * @return
     */
    List<TSDepart> find(TSDepart depart);
}

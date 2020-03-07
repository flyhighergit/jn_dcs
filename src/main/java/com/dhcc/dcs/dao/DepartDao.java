package com.dhcc.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dhcc.dcs.entity.TSDepart;

public interface DepartDao {

    TSDepart getById(String id);

    List<TSDepart> find(TSDepart depart);

    int insert(TSDepart depart);

    int update(TSDepart depart);

    List<TSDepart> find(@Param("carriage") TSDepart depart,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int queryCount(@Param("carriage") TSDepart depart);

}

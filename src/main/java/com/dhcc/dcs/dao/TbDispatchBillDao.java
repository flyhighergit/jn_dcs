package com.dhcc.dcs.dao;

import java.util.List;

import com.dhcc.dcs.entity.TbDispatchBill;
import com.dhcc.dcs.entity.TbDriverVehicle;

/**
 * 
 * 派车单dao接口.
 * 
 * <pre>
 * 	历史记录：
 * 	2020-02-19 zhanglei
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
public interface TbDispatchBillDao {

    public List<TbDispatchBill> find(TbDispatchBill tbDispatchBill);

	public TbDispatchBill getById(String id);

	public int add(TbDispatchBill tbDispatchBill);

	public int updateNotNull(TbDispatchBill tbDispatchBill);

	public int delete(String id);

	public List<TbDispatchBill> findByPage(TbDispatchBill tbDispatchBill, int rowIndex, Integer pageSize);

	public int queryCount(TbDispatchBill tbDispatchBill);

	public List<TbDispatchBill> findByLicense(List<TbDriverVehicle> list, String status, int rowIndex,
			Integer pageSize);

	public int queryCountByLicense(List<TbDriverVehicle> list, String status);

}
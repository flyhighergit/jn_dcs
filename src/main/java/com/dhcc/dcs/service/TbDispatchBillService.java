package com.dhcc.dcs.service;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbDispatchBill;
import com.dhcc.dcs.entity.TbDriverVehicle;

import java.util.List;
/**
 * 
 * 派车单service接口
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
public interface TbDispatchBillService{
	
 	/**
	 * 
	 * <pre>
	 * 	2020-02-19 zhanglei
	 * 	分页查询
	 * </pre>
	 * 
	 * @param tbDispatchBill
	 * @param page
	 * @return
	 */
	public Execution<TbDispatchBill> findByPage(TbDispatchBill tbDispatchBill,Integer pageIndex,Integer pageSize);
	
	/**
	 * 
	 * <pre>
	 * 	2020-02-19 zhanglei
	 * 	查询
	 * </pre>
	 * 
	 * @param tbDispatchBill
	 * @return
	 */
	public List<TbDispatchBill> findBySearch(TbDispatchBill tbDispatchBill);
	
	/**
	 * 
	 * <pre>
	 * 	2020-02-19 zhanglei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param TbDispatchBill
	 * @return
	 */
	public TbDispatchBill getById(String id);
	
	/**
	 * 
	 * <pre>
	 * 	2020-02-19 zhanglei
	 * 	新增
	 * </pre>
	 * 
	 * @param tbDispatchBill
	 */
	public void add(TbDispatchBill tbDispatchBill);
	
	/**
	 * 
	 * <pre>
	 * 	2020-02-19 zhanglei
	 * 	修改
	 * </pre>
	 * 
	 * @param tbDispatchBill
	 */
	public void update(TbDispatchBill tbDispatchBill);
	
	/**
	 * 
	 * <pre>
	 * 	2020-02-19 zhanglei
	 * 	删除
	 * </pre>
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 签收
	 */
	public Boolean signfor(TbDispatchBill tbDispatchBill);

	/**
	 * 根据绑定的车辆查找派车单
	 * @param relationlist
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Execution<TbDispatchBill> findByLicense(List<TbDriverVehicle> relationlist, String status, Integer pageIndex, Integer pageSize);
}

package com.dhcc.dcs.service;

import java.util.List;

import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DriverInfo;

/**
 * 
 * 司机信息service接口
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-19 zhanglei
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
public interface DriverInfoService{
	
 	/**
	 * 
	 * <pre>
	 * 	2019-09-19 zhanglei
	 * 	分页查询
	 * </pre>
	 * 
	 * @param tbDriverInfo
	 * @param page
	 * @return
	 */
	public Execution<DriverInfo> findByPage(DriverInfo tbDriverInfo, Integer pageIndex, Integer pageSize);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-19 zhanglei
	 * 	查询
	 * </pre>
	 * 
	 * @param tbDriverInfo
	 * @return
	 */
	public List<DriverInfo> findBySearch(DriverInfo tbDriverInfo);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-19 zhanglei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param DriverInfo
	 * @return
	 */
	public DriverInfo getById(String id);
	
	public DriverInfo getByFounderId(String id);
	
	public DriverInfo getByIdCord(String id);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-19 zhanglei
	 * 	新增
	 * </pre>
	 * 
	 * @param tbDriverInfo
	 */
	public Execution<DriverInfo> add(DriverInfo tbDriverInfo);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-19 zhanglei
	 * 	修改
	 * </pre>
	 * 
	 * @param tbDriverInfo
	 */
	public Execution<DriverInfo> modify(DriverInfo tbDriverInfo);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-19 zhanglei
	 * 	删除
	 * </pre>
	 * 
	 * @param id
	 */
	public int delete(String id);
	
	public int unbindDriverInfo(String id);
	 
	public int bindDriverInfo(DriverInfo driverInfo);
}

package com.dhcc.dcs.service;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.PhoneLicensePlateInfo;
import java.util.List;

/**
 * 
 * 设备绑定service接口
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-26 zhanglei
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
public interface PhoneLicensePlateInfoService{
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	查询
	 * </pre>
	 * 
	 * @param phoneLicensePlateInfo
	 * @return
	 */
	public List<PhoneLicensePlateInfo> findBySearch(PhoneLicensePlateInfo phoneLicensePlateInfo);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param PhoneLicensePlateInfo
	 * @return
	 */
	public PhoneLicensePlateInfo getById(String id);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	新增
	 * </pre>
	 * 
	 * @param phoneLicensePlateInfo
	 */
	public Execution<PhoneLicensePlateInfo> add(PhoneLicensePlateInfo phoneLicensePlateInfo);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	修改
	 * </pre>
	 * 
	 * @param phoneLicensePlateInfo
	 */
	public Execution<PhoneLicensePlateInfo> update(PhoneLicensePlateInfo phoneLicensePlateInfo);
	
	/**
	 * 
	 * <pre>
	 * 	2019-09-26 zhanglei
	 * 	删除
	 * </pre>
	 * 
	 * @param id
	 */
	public Execution<PhoneLicensePlateInfo> delete(String id);
}

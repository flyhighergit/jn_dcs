package com.dhcc.dcs.service;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbQrCode;
import java.util.List;

/**
 * 
 * 二维码service接口
 * 
 * <pre>
 * 	历史记录：
 * 	2019-11-07 zhanglei
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
public interface TbQrCodeService{
	
	/**
	 * 
	 * <pre>
	 * 	2019-11-07 zhanglei
	 * 	分页查询已经到期但仍在使用的二维码
	 * </pre>
	 * 
	 * @param tbQrCode
	 * @param page
	 * @return
	 */
	public Execution<TbQrCode> findExpireByPage(TbQrCode tbQrCode,Integer pageIndex,Integer pageSize);
	
	/**
	 * 查找即将到期的二维码
	 * @param tbQrCode
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zhanglei
	 */
	public Execution<TbQrCode> findExpireSoonByPage(TbQrCode tbQrCode,Integer pageIndex,Integer pageSize);
	
	/**
	 * 
	 * <pre>
	 * 	2019-11-07 zhanglei
	 * 	查询
	 * </pre>
	 * 
	 * @param tbQrCode
	 * @return
	 */
	public List<TbQrCode> findBySearch(TbQrCode tbQrCode);
	
	/**
	 * 
	 * <pre>
	 * 	2019-11-07 zhanglei
	 * 	通过ID查询
	 * </pre>
	 * 
	 * @param TbQrCode
	 * @return
	 */
	public TbQrCode getById(String id);
	
	/**
	 * 根据keyValue获取二维码记录
	 * @param key
	 * @return
	 * @author zhanglei
	 */
	public TbQrCode getByKey(String key);
	
	/**
	 * 
	 * <pre>
	 * 	2019-11-07 zhanglei
	 * 	新增
	 * </pre>
	 * 
	 * @param tbQrCode
	 */
	public Execution<TbQrCode> add(TbQrCode tbQrCode);
	
	/**
	 * 
	 * <pre>
	 * 	2019-11-07 zhanglei
	 * 	修改
	 * </pre>
	 * 
	 * @param tbQrCode
	 */
	public Execution<TbQrCode> update(TbQrCode tbQrCode);
	
	/**
	 * 
	 * <pre>
	 * 	2019-11-07 zhanglei
	 * 	删除
	 * </pre>
	 * 
	 * @param id
	 */
	public void delete(String id);

	public Execution<TbQrCode> findByPage(TbQrCode tbQrCode,Integer pageIndex,Integer pageSize);

	public int getExpireNumber(TbQrCode tbQrCode);
}

package com.dhcc.dcs.dao;
import java.util.List;

import com.dhcc.dcs.entity.TbQrCode;

/**
 * 
 * 二维码dao接口.
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
public interface TbQrCodeDao {
	TbQrCode getByKey(String key);
	
	List<TbQrCode> findExpireByPage(TbQrCode tbQrCode,int rowIndex,int pageSize);

	int add(TbQrCode tbQrCode);
	
	void delete(String id);

	TbQrCode getById(String id);

	List<TbQrCode> findBySearch(TbQrCode tbQrCode);

	List<TbQrCode> findExpireSoonByPage(TbQrCode tbQrCode, int rowIndex, Integer pageSize);

	int queryCountByExpireSoon(TbQrCode tbQrCode);

	int queryCountByExpire(TbQrCode tbQrCode);

	int updateNotNull(TbQrCode tbQrCode);

	List<TbQrCode> findByPage(TbQrCode tbQrCode, int rowIndex, Integer pageSize);

	int queryCount(TbQrCode tbQrCode);

	int getExpireNumber(TbQrCode tbQrCode);

}
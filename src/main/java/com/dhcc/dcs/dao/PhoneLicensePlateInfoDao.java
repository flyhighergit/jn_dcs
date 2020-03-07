package com.dhcc.dcs.dao;
import java.util.List;

import com.dhcc.dcs.entity.PhoneLicensePlateInfo;

/**
 * 
 * 设备绑定dao接口.
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
public interface PhoneLicensePlateInfoDao {

	List<PhoneLicensePlateInfo> find(PhoneLicensePlateInfo phoneLicensePlateInfo);

	PhoneLicensePlateInfo getById(String id);

	int add(PhoneLicensePlateInfo phoneLicensePlateInfo);

	int update(PhoneLicensePlateInfo phoneLicensePlateInfo);

	int delete(String id);

	int updateNotNull(PhoneLicensePlateInfo phoneLicensePlateInfo);


}
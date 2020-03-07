package com.dhcc.dcs.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.dhcc.dcs.dao.TSUserDao;
import com.dhcc.dcs.entity.TSUser;
import com.dhcc.dcs.service.TSUserService;


/**
 * 
 * 晋能用户拓展表service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2019-12-08 guofei
 * 	新建文件
 * </pre>
 * 
 * @author 
 * <pre>
 * SD
 * 	guofei
 * PG
 *	guofei
 * UT
 *
 * MA
 * </pre>
 * @version $Rev$
 *
 * <p/> $Id$
 *
 */
@Service("tSUserService")
public class TSUserServiceImpl implements TSUserService {

	@Resource
	private TSUserDao tSUserDao;
	
	
	@Override
	public List<TSUser> findBySearch(TSUser tSUser) {

		return tSUserDao.find(tSUser);
	}
	
	@Override
	public TSUser getById(String id) {

		return tSUserDao.getById(id);
	}

	@Override
	public void add(TSUser tSUser) {

		tSUserDao.add(tSUser);
	}
	
	@Override
	public void update(TSUser tSUser) {

		tSUserDao.update(tSUser);
	}

	@Override
	public void delete(String id) {

		tSUserDao.delete(id);
	}
 	
}
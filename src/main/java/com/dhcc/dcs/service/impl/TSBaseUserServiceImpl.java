package com.dhcc.dcs.service.impl;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.dcs.dao.TSBaseUserDao;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.service.TSBaseUserService;

@Service
public class TSBaseUserServiceImpl implements TSBaseUserService {

	@Autowired
	private TSBaseUserDao tSBaseUserDao;

	@Override
	public  TSBaseUser getTSBaseUserByUsernameAndPwd(String username, String password) {
		return tSBaseUserDao.queryTSBaseUserByUserNameAndPwd(username, password);
	}

	@Override
	public TSBaseUser getTSBaseUserById(String id) {
		return tSBaseUserDao.queryTSBaseUserById(id);
	}

	@Override
	@Transactional
	public int save(TSBaseUser tSBaseUser) {
		tSBaseUser.setId(UUID.randomUUID().toString().replace("-", ""));
		return tSBaseUserDao.save(tSBaseUser);
	}

	@Override
	@Transactional
	public int updatePwd(String id, String newPassword) {
		return tSBaseUserDao.updatePwd(id, newPassword);
	}
	
	@Override
	public TSBaseUser getTSBaseUserByUserName(String userName) {
		return tSBaseUserDao.getTSBaseUserByUserName(userName);
	}

	@Override
	public TSBaseUser getTSBaseUserByIdCord(String idCord) {
		return tSBaseUserDao.getTSBaseUserByIdCord(idCord);
	}

	@Override
	public TSBaseUser getTSBaseUserByIdCardAndPwd(String idCord, String password) {
		return tSBaseUserDao.queryTSBaseUserByIdCardAndPwd(idCord, password);
	}

}

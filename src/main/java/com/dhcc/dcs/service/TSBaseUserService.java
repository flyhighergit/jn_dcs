package com.dhcc.dcs.service;

import com.dhcc.dcs.entity.TSBaseUser;

public interface TSBaseUserService {
	/**
	 * 通过帐号和密码获取平台帐号信息
	 * 
	 * @param userName
	 * @return
	 */
	TSBaseUser getTSBaseUserByUsernameAndPwd(String userName, String password);

	/**
	 * 通过userId获取平台帐号信息
	 * 
	 * @param userId
	 * @return
	 */
	TSBaseUser getTSBaseUserById(String id);

	/**
	 * 添加平台帐号
	 * 
	 * @param TSBaseUser
	 * @return
	 */
	int save(TSBaseUser tSBaseUser);

	/**
	 * 通过userId,username,password更改密码
	 * 
	 * @param TSBaseUser
	 * @return
	 */
	int updatePwd(String id,String newPassword);
	
	/**
	 * 通过userName获取平台帐号信息
	 * 
	 * @param userName
	 * @return
	 */
	TSBaseUser getTSBaseUserByUserName(String userName);
	public TSBaseUser getTSBaseUserByIdCord(String idCord);

	TSBaseUser getTSBaseUserByIdCardAndPwd(String idCord, String password);
}

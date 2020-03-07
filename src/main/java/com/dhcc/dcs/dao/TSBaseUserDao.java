package com.dhcc.dcs.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dhcc.dcs.entity.TSBaseUser;

public interface TSBaseUserDao {

	/**
	 * 通过帐号和密码查询对应信息，登录用
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	TSBaseUser queryTSBaseUserByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

	/**
	 * 通过用户Id查询对应TSBaseUser
	 * 
	 * @param userId
	 * @return
	 */
	TSBaseUser queryTSBaseUserById(@Param("id") String id);

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
	int updatePwd(@Param("id") String id, @Param("newPassword") String newPassword );
	
	/**
	 * 通过用户账号查询对应TSBaseUser
	 * 
	 * @param userId
	 * @return
	 */
	public TSBaseUser getTSBaseUserByUserName(String userName);
	public TSBaseUser getTSBaseUserByIdCord(String idCord);
	
	/**
	 * 通过userId更改身份证号
	 * 
	 * @param 
	 * @return
	 */
	int updateIdCord(Map map);

	TSBaseUser queryTSBaseUserByIdCardAndPwd(String idCord, String password);

}

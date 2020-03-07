package com.dhcc.dcs.entity;

/**
 * 系统用户父类表
 * 
 * @author
 */

public class TSBaseUser {

    private String id;
    /**用户名*/
    private String userName;
    /**真实姓名*/
    private String realName;
    /**用户密码*/
    private String password;
    /**状态1：在线,2：离线,0：禁用*/
    private Short status;
    /**所属机构id 多个机构用逗号隔开*/
    private String departid;
    /**所属机构*/
    private TSDepart depart;
    /**是否司机*/
    private String driver;
    /**身份证*/
    private String idCord;
    /**用户类型：4代表司机用户 */
    private String bsUserType ;
    /**删除状态 */
    private String deleteFlag;
    
    public String getIdCord() {
		return idCord;
	}

	public void setIdCord(String idCord) {
		this.idCord = idCord;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public TSDepart getDepart() {
        return depart;
    }

    public void setDepart(TSDepart depart) {
        this.depart = depart;
    }

    public String getBsUserType() {
        return bsUserType;
    }

    public void setBsUserType(String bsUserType) {
        this.bsUserType = bsUserType;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
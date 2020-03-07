package com.dhcc.dcs.entity;
import java.util.Date;

/**
 * 
 * 晋能用户拓展表实体类.
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
public class TSUser {
	
	/**id*/
	private String id;
	/**邮箱*/
	private String email;
	/**手机号*/
	private String mobilephone;
	/**办公座机*/
	private String officephone;
	/**签名文件*/
	private String signaturefile;
	/**修改人*/
	private String updateName;
	/**修改时间*/
	private Date updateDate;
	/**修改人id*/
	private String updateBy;
	/**创建人*/
	private String createName;
	/**创建时间*/
	private Date createDate;
	/**创建人id*/
	private String createBy;
	/**portrait*/
	private String portrait;
	/**imsign*/
	private String imsign;
	/**开发权限标志*/
	private String devFlag;
	/**用户类型*/
	private String userType;
	/**人员类型*/
	private String personType;
	/**性别*/
	private String sex;
	/**工号*/
	private String empNo;
	/**身份证号*/
	private String citizenNo;
	/**传真*/
	private String fax;
	/**联系地址*/
	private String address;
	/**邮编*/
	private String post;
	/**备注*/
	private String memo;
	
	
	/**
	 *方法: 取得String
	 *@return: String  id
	 */
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置String
	 *@param: String  id
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  邮箱
	 */
	public String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置String
	 *@param: String  邮箱
	 */
	public void setEmail(String email){
		this.email = email;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  手机号
	 */
	public String getMobilephone(){
		return this.mobilephone;
	}

	/**
	 *方法: 设置String
	 *@param: String  手机号
	 */
	public void setMobilephone(String mobilephone){
		this.mobilephone = mobilephone;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  办公座机
	 */
	public String getOfficephone(){
		return this.officephone;
	}

	/**
	 *方法: 设置String
	 *@param: String  办公座机
	 */
	public void setOfficephone(String officephone){
		this.officephone = officephone;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  签名文件
	 */
	public String getSignaturefile(){
		return this.signaturefile;
	}

	/**
	 *方法: 设置String
	 *@param: String  签名文件
	 */
	public void setSignaturefile(String signaturefile){
		this.signaturefile = signaturefile;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  修改人
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置String
	 *@param: String  修改人
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  修改时间
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  修改时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  修改人id
	 */
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  修改人id
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  创建人
	 */
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  创建时间
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  创建人id
	 */
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人id
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  portrait
	 */
	public String getPortrait(){
		return this.portrait;
	}

	/**
	 *方法: 设置String
	 *@param: String  portrait
	 */
	public void setPortrait(String portrait){
		this.portrait = portrait;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  imsign
	 */
	public String getImsign(){
		return this.imsign;
	}

	/**
	 *方法: 设置String
	 *@param: String  imsign
	 */
	public void setImsign(String imsign){
		this.imsign = imsign;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  开发权限标志
	 */
	public String getDevFlag(){
		return this.devFlag;
	}

	/**
	 *方法: 设置String
	 *@param: String  开发权限标志
	 */
	public void setDevFlag(String devFlag){
		this.devFlag = devFlag;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  用户类型
	 */
	public String getUserType(){
		return this.userType;
	}

	/**
	 *方法: 设置String
	 *@param: String  用户类型
	 */
	public void setUserType(String userType){
		this.userType = userType;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  人员类型
	 */
	public String getPersonType(){
		return this.personType;
	}

	/**
	 *方法: 设置String
	 *@param: String  人员类型
	 */
	public void setPersonType(String personType){
		this.personType = personType;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  性别
	 */
	public String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置String
	 *@param: String  性别
	 */
	public void setSex(String sex){
		this.sex = sex;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  工号
	 */
	public String getEmpNo(){
		return this.empNo;
	}

	/**
	 *方法: 设置String
	 *@param: String  工号
	 */
	public void setEmpNo(String empNo){
		this.empNo = empNo;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  身份证号
	 */
	public String getCitizenNo(){
		return this.citizenNo;
	}

	/**
	 *方法: 设置String
	 *@param: String  身份证号
	 */
	public void setCitizenNo(String citizenNo){
		this.citizenNo = citizenNo;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  传真
	 */
	public String getFax(){
		return this.fax;
	}

	/**
	 *方法: 设置String
	 *@param: String  传真
	 */
	public void setFax(String fax){
		this.fax = fax;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  联系地址
	 */
	public String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置String
	 *@param: String  联系地址
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  邮编
	 */
	public String getPost(){
		return this.post;
	}

	/**
	 *方法: 设置String
	 *@param: String  邮编
	 */
	public void setPost(String post){
		this.post = post;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备注
	 */
	public String getMemo(){
		return this.memo;
	}

	/**
	 *方法: 设置String
	 *@param: String  备注
	 */
	public void setMemo(String memo){
		this.memo = memo;
	}
	
	
}

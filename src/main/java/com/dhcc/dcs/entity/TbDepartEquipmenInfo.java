package com.dhcc.dcs.entity;
import java.util.Date;


/**
 * 
 * 企业设备管理实体类.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-23 lz
 * 	新建文件
 * </pre>
 * 
 * @author 
 * <pre>
 * SD
 * 	lz
 * PG
 *	lz
 * UT
 *
 * MA
 * </pre>
 * @version $Rev$
 *
 * <p/> $Id$
 *
 */
public class TbDepartEquipmenInfo {
	
	/**id*/
	private String id;
	/**企业编码*/
	private String orgCode;
	/**企业名称*/
	private String orgName;
	/**设备类型*/
	private String equipmentType;
	/**设备编号*/
	private String equipmentCode;
	/**设备名称*/
	private String equipmentName;
	/**设备型号*/
	private String equipmentModel;
	/**设备协议磅秤*/
	private String equipmentAgreement;
	/**设备位置*/
	private String location;
	/**创建人名称*/
	private String createName;
	/**创建人id*/
	private String createId;
	/**创建时间*/
	private Date createTime;
	/**修改人名称*/
	private String updateName;
	/**修改人id*/
	private String updateId;
	/**修改时间*/
	private Date updateTime;
	/**服务url*/
	private String serviceUrl;
	/**数据库url*/
	private String baseUrl;
	/**数据库用户名*/
	private String baseUserName;
	/**数据库用户密码*/
	private String basePassWord;
	/**数据库驱动*/
	private String baseDriver;
	/**数据库类型*/
	private String baseType;
	/**数据库版本*/
	private String baseVersion;
	
	
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
	 *@return: String  企业编码
	 */
	public String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  企业编码
	 */
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  企业名称
	 */
	public String getOrgName(){
		return this.orgName;
	}

	/**
	 *方法: 设置String
	 *@param: String  企业名称
	 */
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  设备类型
	 */
	public String getEquipmentType(){
		return this.equipmentType;
	}

	/**
	 *方法: 设置String
	 *@param: String  设备类型
	 */
	public void setEquipmentType(String equipmentType){
		this.equipmentType = equipmentType;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  设备编号
	 */
	public String getEquipmentCode(){
		return this.equipmentCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  设备编号
	 */
	public void setEquipmentCode(String equipmentCode){
		this.equipmentCode = equipmentCode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  设备名称
	 */
	public String getEquipmentName(){
		return this.equipmentName;
	}

	/**
	 *方法: 设置String
	 *@param: String  设备名称
	 */
	public void setEquipmentName(String equipmentName){
		this.equipmentName = equipmentName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  设备型号
	 */
	public String getEquipmentModel(){
		return this.equipmentModel;
	}

	/**
	 *方法: 设置String
	 *@param: String  设备型号
	 */
	public void setEquipmentModel(String equipmentModel){
		this.equipmentModel = equipmentModel;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  设备协议磅秤
	 */
	public String getEquipmentAgreement(){
		return this.equipmentAgreement;
	}

	/**
	 *方法: 设置String
	 *@param: String  设备协议磅秤
	 */
	public void setEquipmentAgreement(String equipmentAgreement){
		this.equipmentAgreement = equipmentAgreement;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  设备位置
	 */
	public String getLocation(){
		return this.location;
	}

	/**
	 *方法: 设置String
	 *@param: String  设备位置
	 */
	public void setLocation(String location){
		this.location = location;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  创建人名称
	 */
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  创建人id
	 */
	public String getCreateId(){
		return this.createId;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人id
	 */
	public void setCreateId(String createId){
		this.createId = createId;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  创建时间
	 */
	public Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  创建时间
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  修改人名称
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置String
	 *@param: String  修改人名称
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  修改人id
	 */
	public String getUpdateId(){
		return this.updateId;
	}

	/**
	 *方法: 设置String
	 *@param: String  修改人id
	 */
	public void setUpdateId(String updateId){
		this.updateId = updateId;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  修改时间
	 */
	public Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  修改时间
	 */
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  服务url
	 */
	public String getServiceUrl(){
		return this.serviceUrl;
	}

	/**
	 *方法: 设置String
	 *@param: String  服务url
	 */
	public void setServiceUrl(String serviceUrl){
		this.serviceUrl = serviceUrl;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  数据库url
	 */
	public String getBaseUrl(){
		return this.baseUrl;
	}

	/**
	 *方法: 设置String
	 *@param: String  数据库url
	 */
	public void setBaseUrl(String baseUrl){
		this.baseUrl = baseUrl;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  数据库用户名
	 */
	public String getBaseUserName(){
		return this.baseUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  数据库用户名
	 */
	public void setBaseUserName(String baseUserName){
		this.baseUserName = baseUserName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  数据库用户密码
	 */
	public String getBasePassWord(){
		return this.basePassWord;
	}

	/**
	 *方法: 设置String
	 *@param: String  数据库用户密码
	 */
	public void setBasePassWord(String basePassWord){
		this.basePassWord = basePassWord;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  数据库驱动
	 */
	public String getBaseDriver(){
		return this.baseDriver;
	}

	/**
	 *方法: 设置String
	 *@param: String  数据库驱动
	 */
	public void setBaseDriver(String baseDriver){
		this.baseDriver = baseDriver;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  数据库类型
	 */
	public String getBaseType(){
		return this.baseType;
	}

	/**
	 *方法: 设置String
	 *@param: String  数据库类型
	 */
	public void setBaseType(String baseType){
		this.baseType = baseType;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  数据库版本
	 */
	public String getBaseVersion(){
		return this.baseVersion;
	}

	/**
	 *方法: 设置String
	 *@param: String  数据库版本
	 */
	public void setBaseVersion(String baseVersion){
		this.baseVersion = baseVersion;
	}
	
	
}

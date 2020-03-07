package com.dhcc.dcs.entity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 司机信息实体类.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-19 zhanglei
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
public class DriverInfo {
	/**id*/
	private String id;
	/**名称*/
	private String name;
	/**身份证号*/
	private String idCord;
	/**驾驶证号*/
	private String licenseNumber;
	/**驾驶证初次领证日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date licensingDate;
	/**资格证号*/
	private String qualificationCode;
	/**资格证有效期限*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date practitionerLicenseDate;
	/**准驾车型编码*/
	private String modelsCode;
	/**准驾车型*/
	private String models;
	/**有效期限*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date periodValidity;
	/**司机性别*/
	private String sex;
	/**出生日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	/**联系方式*/
	private String phoneNumber;
	/**司机住址*/
	private String address;
	/**创建人*/
	private String founder;
	/**创建人ID*/
	private String founderId;
	/**创建时间*/
	private Date createTime;
	/**修改人*/
	private String modifier;
	/**修改人ID*/
	private String modifierId;
	/**修改时间*/
	private Date updateTime;
	/**数据状态*/
	private String dataStatu;
	/**审核人*/
	private String checkerId;
	/**审核状态*/
	private String checkStatu;
	/**审核时间*/
	private Date checkTime;
	/**所属公司id*/
	private String companyId;
	/**所属公司编码*/
	private String companyCode;
	/**所属公司名称*/
	private String companyName;
	/**所在市id*/
	private String cityId;
	/**所在县id*/
	private String countyId;
	/**所在县编码*/
	private String countyCode;
	/**所在市编码*/
	private String cityCode;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

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
	 *@return: String  名称
	 */
	public String getName(){
		return this.name;
	}

	/**
	 *方法: 设置String
	 *@param: String  名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  身份证号
	 */
	public String getIdCord(){
		return this.idCord;
	}

	/**
	 *方法: 设置String
	 *@param: String  身份证号
	 */
	public void setIdCord(String idCord){
		this.idCord = idCord;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  驾驶证号
	 */
	public String getLicenseNumber(){
		return this.licenseNumber;
	}

	/**
	 *方法: 设置String
	 *@param: String  驾驶证号
	 */
	public void setLicenseNumber(String licenseNumber){
		this.licenseNumber = licenseNumber;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  驾驶证初次领证日期
	 */
	public Date getLicensingDate(){
		return this.licensingDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  驾驶证初次领证日期
	 */
	public void setLicensingDate(Date licensingDate){
		this.licensingDate = licensingDate;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  准驾车型
	 */
	public String getModels(){
		return this.models;
	}

	/**
	 *方法: 设置String
	 *@param: String  准驾车型
	 */
	public void setModels(String models){
		this.models = models;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  有效期限
	 */
	public Date getPeriodValidity(){
		return this.periodValidity;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  有效期限
	 */
	public void setPeriodValidity(Date periodValidity){
		this.periodValidity = periodValidity;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  司机性别
	 */
	public String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置String
	 *@param: String  司机性别
	 */
	public void setSex(String sex){
		this.sex = sex;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  出生日期
	 */
	public Date getBirthday(){
		return this.birthday;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  出生日期
	 */
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  司机住址
	 */
	public String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置String
	 *@param: String  司机住址
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  创建人
	 */
	public String getFounder(){
		return this.founder;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人
	 */
	public void setFounder(String founder){
		this.founder = founder;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  创建人ID
	 */
	public String getFounderId(){
		return this.founderId;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人ID
	 */
	public void setFounderId(String founderId){
		this.founderId = founderId;
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
	 *@return: String  修改人
	 */
	public String getModifier(){
		return this.modifier;
	}

	/**
	 *方法: 设置String
	 *@param: String  修改人
	 */
	public void setModifier(String modifier){
		this.modifier = modifier;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  修改人ID
	 */
	public String getModifierId(){
		return this.modifierId;
	}

	/**
	 *方法: 设置String
	 *@param: String  修改人ID
	 */
	public void setModifierId(String modifierId){
		this.modifierId = modifierId;
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

	public String getDataStatu() {
		return dataStatu;
	}

	public void setDataStatu(String dataStatu) {
		this.dataStatu = dataStatu;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public String getCheckStatu() {
		return checkStatu;
	}

	public void setCheckStatu(String checkStatu) {
		this.checkStatu = checkStatu;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public DriverInfo(String idCord) {
		this.idCord = idCord;
	}

	public DriverInfo() {
	}

	public String getModelsCode() {
		return modelsCode;
	}

	public void setModelsCode(String modelsCode) {
		this.modelsCode = modelsCode;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public Date getPractitionerLicenseDate() {
		return practitionerLicenseDate;
	}

	public void setPractitionerLicenseDate(Date practitionerLicenseDate) {
		this.practitionerLicenseDate = practitionerLicenseDate;
	}
}

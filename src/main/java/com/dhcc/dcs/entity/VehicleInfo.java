package com.dhcc.dcs.entity;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 车辆信息实体类.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-10-20 zhanglei
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
public class VehicleInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**id*/
	private String id;
	/**车牌号*/
	private String licensePlate;
	/**所属公司*/
	private String sysCompanyName;
	/**所属公司id*/
	private String sysCompanyCode;
	/**轴数*/
	private Integer axles;
	/**核载*/
	private Double maxPayload;
	/**资格证号*/
	private String qualificationCode;
	/**资格证书路径*/
	private String qualificationPath;
	/**运营证号*/
	private String tradingCode;
	/**运营证书路径*/
	private String tradingPath;
	/**行车证号*/
	private String licenceDisc;
	/**行车证书路径*/
	private String licenceDiscPath;
	/**发动机号*/
	private String engineNum;
	/**车架号*/
	private String frameNum;
	/**驾驶证号*/
	private String driverLicense;
	/**驾驶证路径*/
	private String driverLicensePath;
	/**车辆持有人*/
	private String holder;
	/**车辆识别号*/
	private String vin;
	/**电话号码*/
	private String phoneNumber;
	/**地址*/
	private String address;
	/**备注*/
	private String remark;
	/**修改时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	/**创建时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	/**车主性别*/
	private String sexOwner;
	private String sexOwnerName;
	/**车主出生日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bornOwner;
	/**车主身份证号*/
	private String numberOwner;
	/**vehicleTypeName*/
	private String vehicleTypeName;
	/**车辆类型*/
	private String vehicleType;
	/**使用性质*/
	private String natureUse;
	/**品牌型号*/
	private String brandModel;
	/**注号牌号码日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date numberDate;
	/**行车证发证日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	/**车主国籍*/
	private String nationality;
	/**驾驶证初次领证日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date licensingDate;
	/**准驾车型*/
	private String models;
	/**准驾车型编码*/
	private String modelsCode;
	/**有效期限*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date periodValidity;
	/**从业资格类型*/
	private String typeQualification;
	/**资格证初次发证时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date issuingTime;
	/**发证机关*/
	private String issuingAuthority;
	/**业户名称*/
	private String businessName;
	/**业户地址*/
	private String businessAddress;
	/**经济类型*/
	private String economicType;
	/**吨位*/
	private String tonnage;
	/**车辆长*/
	private String vehicleLength;
	/**车辆宽*/
	private String vehicleWidth;
	/**车辆高*/
	private String vehicleHeight;
	/**经营范围*/
	private String businessScope;
	/**身份证图片路径*/
	private String cardPath;
	/**数据状态*/
	private String status;
	/**核对状态*/
	private String checkStatus;
	/**创建人*/
	private String founder;
	/**创建人ID*/
	private String founderId;
	/**修改人*/
	private String modifier;
	/**修改人ID*/
	private String modifierId;
	/**身份证住址*/
	private String addressOwner;
	/**审核人*/
	private String checkerId;
	/**审核时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkTime;
	
	/**行驶证有效期限*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date travelLicenseDate;
	/**驾驶证有限期限*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date driveLicenseDate;
	/**从业者有效期限*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date practitionerLicenseDate;
	/**道路运输证有效期限*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date transportLicenseDate;
	/**道路运输证发证日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date transportIssueDate;
	/**车辆颜色编码*/
	private String brandColor;
	/**车辆颜色*/
	private String brandColorName;
	
	/**绑定的车辆状态 0非默认 1默认*/
	private String bindStatus;
	
	
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
	 *@return: String  车牌号
	 */
	public String getLicensePlate(){
		return this.licensePlate;
	}

	/**
	 *方法: 设置String
	 *@param: String  车牌号
	 */
	public void setLicensePlate(String licensePlate){
		this.licensePlate = licensePlate;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  所属公司
	 */
	public String getSysCompanyName(){
		return this.sysCompanyName;
	}

	/**
	 *方法: 设置String
	 *@param: String  所属公司
	 */
	public void setSysCompanyName(String sysCompanyName){
		this.sysCompanyName = sysCompanyName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  所属公司id
	 */
	public String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  所属公司id
	 */
	public void setSysCompanyCode(String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	
	/**
	 *方法: 取得Integer
	 *@return: Integer  轴数
	 */
	public Integer getAxles(){
		return this.axles;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  轴数
	 */
	public void setAxles(Integer axles){
		this.axles = axles;
	}
	
	/**
	 *方法: 取得Double
	 *@return: Double  核载
	 */
	public Double getMaxPayload(){
		return this.maxPayload;
	}

	/**
	 *方法: 设置Double
	 *@param: Double  核载
	 */
	public void setMaxPayload(Double maxPayload){
		this.maxPayload = maxPayload;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  资格证号
	 */
	public String getQualificationCode(){
		return this.qualificationCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  资格证号
	 */
	public void setQualificationCode(String qualificationCode){
		this.qualificationCode = qualificationCode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  资格证书路径
	 */
	public String getQualificationPath(){
		return this.qualificationPath;
	}

	/**
	 *方法: 设置String
	 *@param: String  资格证书路径
	 */
	public void setQualificationPath(String qualificationPath){
		this.qualificationPath = qualificationPath;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  运营证号
	 */
	public String getTradingCode(){
		return this.tradingCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  运营证号
	 */
	public void setTradingCode(String tradingCode){
		this.tradingCode = tradingCode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  运营证书路径
	 */
	public String getTradingPath(){
		return this.tradingPath;
	}

	/**
	 *方法: 设置String
	 *@param: String  运营证书路径
	 */
	public void setTradingPath(String tradingPath){
		this.tradingPath = tradingPath;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  行车证号
	 */
	public String getLicenceDisc(){
		return this.licenceDisc;
	}

	/**
	 *方法: 设置String
	 *@param: String  行车证号
	 */
	public void setLicenceDisc(String licenceDisc){
		this.licenceDisc = licenceDisc;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  行车证书路径
	 */
	public String getLicenceDiscPath(){
		return this.licenceDiscPath;
	}

	/**
	 *方法: 设置String
	 *@param: String  行车证书路径
	 */
	public void setLicenceDiscPath(String licenceDiscPath){
		this.licenceDiscPath = licenceDiscPath;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  发动机号
	 */
	public String getEngineNum(){
		return this.engineNum;
	}

	/**
	 *方法: 设置String
	 *@param: String  发动机号
	 */
	public void setEngineNum(String engineNum){
		this.engineNum = engineNum;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车架号
	 */
	public String getFrameNum(){
		return this.frameNum;
	}

	/**
	 *方法: 设置String
	 *@param: String  车架号
	 */
	public void setFrameNum(String frameNum){
		this.frameNum = frameNum;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  驾驶证号
	 */
	public String getDriverLicense(){
		return this.driverLicense;
	}

	/**
	 *方法: 设置String
	 *@param: String  驾驶证号
	 */
	public void setDriverLicense(String driverLicense){
		this.driverLicense = driverLicense;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  驾驶证路径
	 */
	public String getDriverLicensePath(){
		return this.driverLicensePath;
	}

	/**
	 *方法: 设置String
	 *@param: String  驾驶证路径
	 */
	public void setDriverLicensePath(String driverLicensePath){
		this.driverLicensePath = driverLicensePath;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车辆持有人
	 */
	public String getHolder(){
		return this.holder;
	}

	/**
	 *方法: 设置String
	 *@param: String  车辆持有人
	 */
	public void setHolder(String holder){
		this.holder = holder;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车辆识别号
	 */
	public String getVin(){
		return this.vin;
	}

	/**
	 *方法: 设置String
	 *@param: String  车辆识别号
	 */
	public void setVin(String vin){
		this.vin = vin;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  电话号码
	 */
	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	/**
	 *方法: 设置String
	 *@param: String  电话号码
	 */
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  地址
	 */
	public String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置String
	 *@param: String  地址
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备注
	 */
	public String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置String
	 *@param: String  备注
	 */
	public void setRemark(String remark){
		this.remark = remark;
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
	 *@return: String  车主性别
	 */
	public String getSexOwner(){
		return this.sexOwner;
	}

	/**
	 *方法: 设置String
	 *@param: String  车主性别
	 */
	public void setSexOwner(String sexOwner){
		this.sexOwner = sexOwner;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  车主出生日期
	 */
	public Date getBornOwner(){
		return this.bornOwner;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  车主出生日期
	 */
	public void setBornOwner(Date bornOwner){
		this.bornOwner = bornOwner;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车主身份证号
	 */
	public String getNumberOwner(){
		return this.numberOwner;
	}

	/**
	 *方法: 设置String
	 *@param: String  车主身份证号
	 */
	public void setNumberOwner(String numberOwner){
		this.numberOwner = numberOwner;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  vehicleTypeName
	 */
	public String getVehicleTypeName(){
		return this.vehicleTypeName;
	}

	/**
	 *方法: 设置String
	 *@param: String  vehicleTypeName
	 */
	public void setVehicleTypeName(String vehicleTypeName){
		this.vehicleTypeName = vehicleTypeName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车辆类型
	 */
	public String getVehicleType(){
		return this.vehicleType;
	}

	/**
	 *方法: 设置String
	 *@param: String  车辆类型
	 */
	public void setVehicleType(String vehicleType){
		this.vehicleType = vehicleType;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  使用性质
	 */
	public String getNatureUse(){
		return this.natureUse;
	}

	/**
	 *方法: 设置String
	 *@param: String  使用性质
	 */
	public void setNatureUse(String natureUse){
		this.natureUse = natureUse;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  品牌型号
	 */
	public String getBrandModel(){
		return this.brandModel;
	}

	/**
	 *方法: 设置String
	 *@param: String  品牌型号
	 */
	public void setBrandModel(String brandModel){
		this.brandModel = brandModel;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  注号牌号码日期
	 */
	public Date getNumberDate(){
		return this.numberDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  注号牌号码日期
	 */
	public void setNumberDate(Date numberDate){
		this.numberDate = numberDate;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  行车证发证日期
	 */
	public Date getIssueDate(){
		return this.issueDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  行车证发证日期
	 */
	public void setIssueDate(Date issueDate){
		this.issueDate = issueDate;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车主国籍
	 */
	public String getNationality(){
		return this.nationality;
	}

	/**
	 *方法: 设置String
	 *@param: String  车主国籍
	 */
	public void setNationality(String nationality){
		this.nationality = nationality;
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
	 *@return: String  从业资格类型
	 */
	public String getTypeQualification(){
		return this.typeQualification;
	}

	/**
	 *方法: 设置String
	 *@param: String  从业资格类型
	 */
	public void setTypeQualification(String typeQualification){
		this.typeQualification = typeQualification;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  资格证初次发证时间
	 */
	public Date getIssuingTime(){
		return this.issuingTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  资格证初次发证时间
	 */
	public void setIssuingTime(Date issuingTime){
		this.issuingTime = issuingTime;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  发证机关
	 */
	public String getIssuingAuthority(){
		return this.issuingAuthority;
	}

	/**
	 *方法: 设置String
	 *@param: String  发证机关
	 */
	public void setIssuingAuthority(String issuingAuthority){
		this.issuingAuthority = issuingAuthority;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  业户名称
	 */
	public String getBusinessName(){
		return this.businessName;
	}

	/**
	 *方法: 设置String
	 *@param: String  业户名称
	 */
	public void setBusinessName(String businessName){
		this.businessName = businessName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  业户地址
	 */
	public String getBusinessAddress(){
		return this.businessAddress;
	}

	/**
	 *方法: 设置String
	 *@param: String  业户地址
	 */
	public void setBusinessAddress(String businessAddress){
		this.businessAddress = businessAddress;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  经济类型
	 */
	public String getEconomicType(){
		return this.economicType;
	}

	/**
	 *方法: 设置String
	 *@param: String  经济类型
	 */
	public void setEconomicType(String economicType){
		this.economicType = economicType;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  吨位
	 */
	public String getTonnage(){
		return this.tonnage;
	}

	/**
	 *方法: 设置String
	 *@param: String  吨位
	 */
	public void setTonnage(String tonnage){
		this.tonnage = tonnage;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车辆长
	 */
	public String getVehicleLength(){
		return this.vehicleLength;
	}

	/**
	 *方法: 设置String
	 *@param: String  车辆长
	 */
	public void setVehicleLength(String vehicleLength){
		this.vehicleLength = vehicleLength;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车辆宽
	 */
	public String getVehicleWidth(){
		return this.vehicleWidth;
	}

	/**
	 *方法: 设置String
	 *@param: String  车辆宽
	 */
	public void setVehicleWidth(String vehicleWidth){
		this.vehicleWidth = vehicleWidth;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车辆高
	 */
	public String getVehicleHeight(){
		return this.vehicleHeight;
	}

	/**
	 *方法: 设置String
	 *@param: String  车辆高
	 */
	public void setVehicleHeight(String vehicleHeight){
		this.vehicleHeight = vehicleHeight;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  经营范围
	 */
	public String getBusinessScope(){
		return this.businessScope;
	}

	/**
	 *方法: 设置String
	 *@param: String  经营范围
	 */
	public void setBusinessScope(String businessScope){
		this.businessScope = businessScope;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  身份证图片路径
	 */
	public String getCardPath(){
		return this.cardPath;
	}

	/**
	 *方法: 设置String
	 *@param: String  身份证图片路径
	 */
	public void setCardPath(String cardPath){
		this.cardPath = cardPath;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  数据状态
	 */
	public String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置String
	 *@param: String  数据状态
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  核对状态
	 */
	public String getCheckStatus(){
		return this.checkStatus;
	}

	/**
	 *方法: 设置String
	 *@param: String  核对状态
	 */
	public void setCheckStatus(String checkStatus){
		this.checkStatus = checkStatus;
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
	 *方法: 取得String
	 *@return: String  身份证住址
	 */
	public String getAddressOwner(){
		return this.addressOwner;
	}

	/**
	 *方法: 设置String
	 *@param: String  身份证住址
	 */
	public void setAddressOwner(String addressOwner){
		this.addressOwner = addressOwner;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  审核人
	 */
	public String getCheckerId(){
		return this.checkerId;
	}

	/**
	 *方法: 设置String
	 *@param: String  审核人
	 */
	public void setCheckerId(String checkerId){
		this.checkerId = checkerId;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  审核时间
	 */
	public Date getCheckTime(){
		return this.checkTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  审核时间
	 */
	public void setCheckTime(Date checkTime){
		this.checkTime = checkTime;
	}

	public String getSexOwnerName() {
		return sexOwnerName;
	}

	public void setSexOwnerName(String sexOwnerName) {
		this.sexOwnerName = sexOwnerName;
	}

	public String getModelsCode() {
		return modelsCode;
	}

	public void setModelsCode(String modelsCode) {
		this.modelsCode = modelsCode;
	}

	public Date getTravelLicenseDate() {
		return travelLicenseDate;
	}

	public void setTravelLicenseDate(Date travelLicenseDate) {
		this.travelLicenseDate = travelLicenseDate;
	}

	public Date getDriveLicenseDate() {
		return driveLicenseDate;
	}

	public void setDriveLicenseDate(Date driveLicenseDate) {
		this.driveLicenseDate = driveLicenseDate;
	}

	public Date getPractitionerLicenseDate() {
		return practitionerLicenseDate;
	}

	public void setPractitionerLicenseDate(Date practitionerLicenseDate) {
		this.practitionerLicenseDate = practitionerLicenseDate;
	}

	public Date getTransportLicenseDate() {
		return transportLicenseDate;
	}

	public void setTransportLicenseDate(Date transportLicenseDate) {
		this.transportLicenseDate = transportLicenseDate;
	}

	public Date getTransportIssueDate() {
		return transportIssueDate;
	}

	public void setTransportIssueDate(Date transportIssueDate) {
		this.transportIssueDate = transportIssueDate;
	}

	public String getBrandColor() {
		return brandColor;
	}

	public void setBrandColor(String brandColor) {
		this.brandColor = brandColor;
	}

	public String getBrandColorName() {
		return brandColorName;
	}

	public void setBrandColorName(String brandColorName) {
		this.brandColorName = brandColorName;
	}

	public String getBindStatus() {
		return bindStatus;
	}

	public void setBindStatus(String bindStatus) {
		this.bindStatus = bindStatus;
	}
	
}

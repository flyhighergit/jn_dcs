package com.dhcc.dcs.entity;
import java.util.Date;


/**
 * 
 * 涉煤关联表实体类.
 * 
 *
 */
public class TbCoalRelated {	
	/**主键*/
	private String id;
	/**编码*/
	private String code;
	/**名称*/
	private String name;
	/**简称*/
	private String orgShortName;
	/**所属县id*/
	private String parentId;
	/**所属县名称*/
	private String parentName;
	/**所属县编码*/
	private String parentCode;
	/**所属县简称*/
	private String parentShortName;
	/**创建人名称*/
	private String createName;
	/**创建人id*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**更新人名称*/
	private String updateName;
	/**更新人id*/
	private String updateBy;
	/**更新日期*/
	private Date updateDate;
	/**机构级别*/
	private String orgLevel;
	/**经度*/
	private String jd;
	/**纬度*/
	private String wd;
	/**运营模式：购销合同*/
	private String opPurchase;
	/**运营模式：派车单*/
	private String opDispatch;
	/**运营模式：承运合同*/
	private String opCarriage;
	/**运营模式：无*/
	private String opNone;
	/**公司地址*/
	private String address;
	/**法定代表人*/
	private String legalPerson;
	/**委托代理人*/
	private String agent;
	/**电报/挂号*/
	private String telegraph;
	/**电话*/
	private String telephone;
	/**开户银行*/
	private String openingBank;
	/**银行账户*/
	private String bankAccount;
	/**纳税人登记号*/
	private String taxpayerIdentification;
	/**邮政编码*/
	private String postalCode;
	/**是否为采集点*/
	private String isCollect;
	/**企业类型1生产企业、2洗选加工企业、3、经营企业、4承运企业*/
	private String companyType;
	/**所属企业id*/
	private String parnetOrgId;	
	/**所属企业编码*/
	private String parentOrgCode;	
	/**所属企业名称*/
	private String parentOrgName;	
	/**所属企业简称*/
	private String parentOrgShortName;	
	/**机构类型编码1行政区域 2.企业*/
	private String orgTypeCode;
	/**机构类型名称*/
	private String orgTypeName;
	/**企业行业归属编码*/
	private String orgIndustryCode;
	/**企业行业归属名称（该企业的行业类型，包括露天矿、洗煤厂等）*/
	private String orgIndustryName;
	/**审核状态*/
	private String checkStatus;
	/**审核人ID*/
	private String checkerId;
	/**审核时间*/
	private Date checkTime;
	/**是否开通 1开通 0 未开通 */
	private String isOpening;
	/**公司简介*/
	private String profile;
	/**企业属性编码*/
	private String attributeCode;
	/**企业属性名称*/
	private String attributeName;
	/**磅房作业模式*/
	private String poundStyle;
	
	
	
	/**
	 *方法: 取得String
	 *@return: String  主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置String
	 *@param: String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  编码
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置String
	 *@param: String  编码
	 */
	public void setCode(String code){
		this.code = code;
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
	 *方法: 取得Date
	 *@return: Date  创建日期
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  更新人名称
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置String
	 *@param: String  更新人名称
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  更新日期
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  更新日期
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getOpPurchase() {
		return opPurchase;
	}

	public void setOpPurchase(String opPurchase) {
		this.opPurchase = opPurchase;
	}

	public String getOpDispatch() {
		return opDispatch;
	}

	public void setOpDispatch(String opDispatch) {
		this.opDispatch = opDispatch;
	}

	public String getOpCarriage() {
		return opCarriage;
	}

	public void setOpCarriage(String opCarriage) {
		this.opCarriage = opCarriage;
	}

	public String getOpNone() {
		return opNone;
	}

	public void setOpNone(String opNone) {
		this.opNone = opNone;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentShortName() {
		return parentShortName;
	}

	public void setParentShortName(String parentShortName) {
		this.parentShortName = parentShortName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getTelegraph() {
		return telegraph;
	}

	public void setTelegraph(String telegraph) {
		this.telegraph = telegraph;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getTaxpayerIdentification() {
		return taxpayerIdentification;
	}

	public void setTaxpayerIdentification(String taxpayerIdentification) {
		this.taxpayerIdentification = taxpayerIdentification;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getParnetOrgId() {
		return parnetOrgId;
	}

	public void setParnetOrgId(String parnetOrgId) {
		this.parnetOrgId = parnetOrgId;
	}

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getParentOrgShortName() {
		return parentOrgShortName;
	}

	public void setParentOrgShortName(String parentOrgShortName) {
		this.parentOrgShortName = parentOrgShortName;
	}

	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getOrgIndustryCode() {
		return orgIndustryCode;
	}

	public void setOrgIndustryCode(String orgIndustryCode) {
		this.orgIndustryCode = orgIndustryCode;
	}

	public String getOrgIndustryName() {
		return orgIndustryName;
	}

	public void setOrgIndustryName(String orgIndustryName) {
		this.orgIndustryName = orgIndustryName;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getIsOpening() {
		return isOpening;
	}

	public void setIsOpening(String isOpening) {
		this.isOpening = isOpening;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getAttributeCode() {
		return attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getPoundStyle() {
		return poundStyle;
	}

	public void setPoundStyle(String poundStyle) {
		this.poundStyle = poundStyle;
	}
	
}

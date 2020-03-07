package com.dhcc.dcs.entity;

import java.util.Date;

/**
 * 
 * 称重记录表实体类.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-10-04 zhanglei
 * 	新建文件
 * </pre>
 * 
 * @author
 * 
 *         <pre>
 * SD
 * 	zhanglei
 * PG
 *	zhanglei
 * UT
 *
 * MA
 *         </pre>
 * 
 * @version $Rev$
 *
 *          <p/>
 *          $Id$
 *
 */
public class TbPoundRecords {

	/** id */
	private String id;
	/** 公司编号 */
	private String companyCode;
	/** 公司名称 */
	private String companyName;
	/** 空车磅秤编码 */
	private String firstBalanceCode;
	/** 空车磅秤称重 */
	private Double firstWeight;
	/** 空车磅秤称重时间 */
	private Date firstTime;
	/** 空车确认人id */
	private String firstConfirmingId;
	/** 空车确认人名称 */
	private String firstConfirmingName;
	/** 空车确认时间 */
	private Date firstConfirmingTime;
	/** 载货磅秤编码 */
	private String secondCode;
	/** 载货磅秤称重 */
	private Double secondWeight;
	/** 载货磅秤称重时间 */
	private Date secondTime;
	/** 载货确认人id */
	private String secondConfirmingId;
	/** 载货确认人名称 */
	private String secondConfirmingName;
	/** 载货确认时间 */
	private Date secondConfirmingTime;
	/** 车牌号 */
	private String licensePlate;
	/** 派单id */
	private String dispatchingId;
	/** 司机 */
	private String driver;
	/** 记录表状态0进行中1结束 */
	private String status;
	/** 购销合同id */
	private String purchaseContractId;
	/** 承运合同id */
	private String carriageContractId;
	/** 合同类型编码 */
	private String contractTypeCode;
	/** 物料编码 */
	private String wlCode;
	/** 物料名称 */
	private String wlName;
	/** 卖方名称 */
	private String sellerName;
	/** 卖方ID */
	private String sellerId;
	/** 买方名称 */
	private String buyerName;
	/** 异常标识 */
	private String exceptionFlg;
	/** 异常处理标识 */
	private String exceptionDealFlg;
	/** 人工编辑标识 */
	private String personEditFlg;
	/** 车牌号手动 */
	private String licensePlateHand;
	/** 派车单id手动 */
	private String dispatchingIdHand;
	/** 购销合同id手动 */
	private String purchaseContractIdHand;
	/** 承运合同id手动 */
	private String carriageContractIdHand;
	/** 合同类型手动 */
	private String contractTypeCodeHand;
	/** 物料编码手动 */
	private String wlCodeHand;
	/** 物料名称手动 */
	private String wlNameHand;
	/** 卖方名称手动 */
	private String sellerNameHand;
	/** 卖方手动 */
	private String sellerIdHand;
	/** 买方手动 */
	private String buyerNameHand;
	/**确认人ID */
	private String confirmId;
	/** 确认人名称 */
	private String confirmName;
	/** 确认时间 */
	private String confirmTime;
	/** 处理人ID */
	private String dealId;
	/** 处理人姓名 */
	private String dealName;
	/** 处理时间 */
	private String dealTime;
	/** 更新人ID */
	private String updateId;
	/** 更新人名称 */
	private String updateName;
	/** 更新时间 */
	private String updateTime;
	/** 司机ID手动 */
	private String driverHand;
	/** 作业类型*/
	private String taskType;
	/** 买方id */
	private String buyerId;
	/** 买方id手动 */
	private String buyerIdHand;
	/** 承运单位ID */
	private String carrierId;
	/** 承运单位ID手动 */
	private String carrierIdHand;
	/** 承运单位名称 */
	private String carrierUnit;
	/** 承运单位名称手动 */
	private String carrierUnitHand;
	/** 合并状态1，合并数据,2合并前 */
	private String mergeState;
	/** 合并后的数据ID */
	private String mergeId;

	/**
	 * 方法: 取得String
	 * 
	 * @return: String id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 公司编号
	 */
	public String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 公司编号
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 公司名称
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 空车磅秤编码
	 */
	public String getFirstBalanceCode() {
		return this.firstBalanceCode;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 空车磅秤编码
	 */
	public void setFirstBalanceCode(String firstBalanceCode) {
		this.firstBalanceCode = firstBalanceCode;
	}

	/**
	 * 方法: 取得Double
	 * 
	 * @return: Double 空车磅秤称重
	 */
	public Double getFirstWeight() {
		return this.firstWeight;
	}

	/**
	 * 方法: 设置Double
	 * 
	 * @param: Double 空车磅秤称重
	 */
	public void setFirstWeight(Double firstWeight) {
		this.firstWeight = firstWeight;
	}

	/**
	 * 方法: 取得Date
	 * 
	 * @return: Date 空车磅秤称重时间
	 */
	public Date getFirstTime() {
		return this.firstTime;
	}

	/**
	 * 方法: 设置Date
	 * 
	 * @param: Date 空车磅秤称重时间
	 */
	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 空车确认人id
	 */
	public String getFirstConfirmingId() {
		return this.firstConfirmingId;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 空车确认人id
	 */
	public void setFirstConfirmingId(String firstConfirmingId) {
		this.firstConfirmingId = firstConfirmingId;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 空车确认人名称
	 */
	public String getFirstConfirmingName() {
		return this.firstConfirmingName;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 空车确认人名称
	 */
	public void setFirstConfirmingName(String firstConfirmingName) {
		this.firstConfirmingName = firstConfirmingName;
	}

	/**
	 * 方法: 取得Date
	 * 
	 * @return: Date 空车确认时间
	 */
	public Date getFirstConfirmingTime() {
		return this.firstConfirmingTime;
	}

	/**
	 * 方法: 设置Date
	 * 
	 * @param: Date 空车确认时间
	 */
	public void setFirstConfirmingTime(Date firstConfirmingTime) {
		this.firstConfirmingTime = firstConfirmingTime;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 载货磅秤编码
	 */
	public String getSecondCode() {
		return this.secondCode;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 载货磅秤编码
	 */
	public void setSecondCode(String secondCode) {
		this.secondCode = secondCode;
	}

	/**
	 * 方法: 取得Double
	 * 
	 * @return: Double 载货磅秤称重
	 */
	public Double getSecondWeight() {
		return this.secondWeight;
	}

	/**
	 * 方法: 设置Double
	 * 
	 * @param: Double 载货磅秤称重
	 */
	public void setSecondWeight(Double secondWeight) {
		this.secondWeight = secondWeight;
	}

	/**
	 * 方法: 取得Date
	 * 
	 * @return: Date 载货磅秤称重时间
	 */
	public Date getSecondTime() {
		return this.secondTime;
	}

	/**
	 * 方法: 设置Date
	 * 
	 * @param: Date 载货磅秤称重时间
	 */
	public void setSecondTime(Date secondTime) {
		this.secondTime = secondTime;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 载货确认人id
	 */
	public String getSecondConfirmingId() {
		return this.secondConfirmingId;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 载货确认人id
	 */
	public void setSecondConfirmingId(String secondConfirmingId) {
		this.secondConfirmingId = secondConfirmingId;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 载货确认人名称
	 */
	public String getSecondConfirmingName() {
		return this.secondConfirmingName;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 载货确认人名称
	 */
	public void setSecondConfirmingName(String secondConfirmingName) {
		this.secondConfirmingName = secondConfirmingName;
	}

	/**
	 * 方法: 取得Date
	 * 
	 * @return: Date 载货确认时间
	 */
	public Date getSecondConfirmingTime() {
		return this.secondConfirmingTime;
	}

	/**
	 * 方法: 设置Date
	 * 
	 * @param: Date 载货确认时间
	 */
	public void setSecondConfirmingTime(Date secondConfirmingTime) {
		this.secondConfirmingTime = secondConfirmingTime;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 车牌号
	 */
	public String getLicensePlate() {
		return this.licensePlate;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 车牌号
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 派单id
	 */
	public String getDispatchingId() {
		return this.dispatchingId;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 派单id
	 */
	public void setDispatchingId(String dispatchingId) {
		this.dispatchingId = dispatchingId;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 司机
	 */
	public String getDriver() {
		return this.driver;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 司机
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 记录表状态0进行中1结束
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 记录表状态0进行中1结束
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 购销合同id
	 */
	public String getPurchaseContractId() {
		return this.purchaseContractId;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 购销合同id
	 */
	public void setPurchaseContractId(String purchaseContractId) {
		this.purchaseContractId = purchaseContractId;
	}

	/**
	 * 方法: 取得String
	 * 
	 * @return: String 承运合同id
	 */
	public String getCarriageContractId() {
		return this.carriageContractId;
	}

	/**
	 * 方法: 设置String
	 * 
	 * @param: String 承运合同id
	 */
	public void setCarriageContractId(String carriageContractId) {
		this.carriageContractId = carriageContractId;
	}

	public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getCarrierIdHand() {
		return carrierIdHand;
	}

	public void setCarrierIdHand(String carrierIdHand) {
		this.carrierIdHand = carrierIdHand;
	}

	public String getCarrierUnit() {
		return carrierUnit;
	}

	public void setCarrierUnit(String carrierUnit) {
		this.carrierUnit = carrierUnit;
	}

	public String getCarrierUnitHand() {
		return carrierUnitHand;
	}

	public void setCarrierUnitHand(String carrierUnitHand) {
		this.carrierUnitHand = carrierUnitHand;
	}

	public String getMergeState() {
		return mergeState;
	}

	public void setMergeState(String mergeState) {
		this.mergeState = mergeState;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getContractTypeCode() {
		return contractTypeCode;
	}

	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}

	public String getWlCode() {
		return wlCode;
	}

	public void setWlCode(String wlCode) {
		this.wlCode = wlCode;
	}

	public String getWlName() {
		return wlName;
	}

	public void setWlName(String wlName) {
		this.wlName = wlName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getExceptionFlg() {
		return exceptionFlg;
	}

	public void setExceptionFlg(String exceptionFlg) {
		this.exceptionFlg = exceptionFlg;
	}

	public String getExceptionDealFlg() {
		return exceptionDealFlg;
	}

	public void setExceptionDealFlg(String exceptionDealFlg) {
		this.exceptionDealFlg = exceptionDealFlg;
	}

	public String getPersonEditFlg() {
		return personEditFlg;
	}

	public void setPersonEditFlg(String personEditFlg) {
		this.personEditFlg = personEditFlg;
	}

	public String getLicensePlateHand() {
		return licensePlateHand;
	}

	public void setLicensePlateHand(String licensePlateHand) {
		this.licensePlateHand = licensePlateHand;
	}

	public String getDispatchingIdHand() {
		return dispatchingIdHand;
	}

	public void setDispatchingIdHand(String dispatchingIdHand) {
		this.dispatchingIdHand = dispatchingIdHand;
	}

	public String getPurchaseContractIdHand() {
		return purchaseContractIdHand;
	}

	public void setPurchaseContractIdHand(String purchaseContractIdHand) {
		this.purchaseContractIdHand = purchaseContractIdHand;
	}

	public String getCarriageContractIdHand() {
		return carriageContractIdHand;
	}

	public void setCarriageContractIdHand(String carriageContractIdHand) {
		this.carriageContractIdHand = carriageContractIdHand;
	}

	public String getContractTypeCodeHand() {
		return contractTypeCodeHand;
	}

	public void setContractTypeCodeHand(String contractTypeCodeHand) {
		this.contractTypeCodeHand = contractTypeCodeHand;
	}

	public String getWlCodeHand() {
		return wlCodeHand;
	}

	public void setWlCodeHand(String wlCodeHand) {
		this.wlCodeHand = wlCodeHand;
	}

	public String getWlNameHand() {
		return wlNameHand;
	}

	public void setWlNameHand(String wlNameHand) {
		this.wlNameHand = wlNameHand;
	}

	public String getSellerNameHand() {
		return sellerNameHand;
	}

	public void setSellerNameHand(String sellerNameHand) {
		this.sellerNameHand = sellerNameHand;
	}

	public String getSellerIdHand() {
		return sellerIdHand;
	}

	public void setSellerIdHand(String sellerIdHand) {
		this.sellerIdHand = sellerIdHand;
	}

	public String getBuyerNameHand() {
		return buyerNameHand;
	}

	public void setBuyerNameHand(String buyerNameHand) {
		this.buyerNameHand = buyerNameHand;
	}

	public String getConfirmId() {
		return confirmId;
	}

	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}

	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDriverHand() {
		return driverHand;
	}

	public void setDriverHand(String driverHand) {
		this.driverHand = driverHand;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerIdHand() {
		return buyerIdHand;
	}

	public void setBuyerIdHand(String buyerIdHand) {
		this.buyerIdHand = buyerIdHand;
	}

}

package com.dhcc.dcs.entity;
import java.util.Date;

/**
 * 
 * 派车信息实体类.
 */
public class DispatchingInfo {
	/** 主键ID */
    private String id;
    /** 车牌号 */
    private String licensePlate;
    /**涉煤企业编号（也即煤场、起运地）*/
	private String coalRelatedCode;
    /** 用车时间 */
    private Date serviceTime;
    /** 用车事由 */
    private String serviceRemark;
    /** 起运地 */
    private String deliveryPlace;
    /** 目的地 */
    private String receiptPlace;
    /** 搭乘人员 */
    private String passenger;
    /** 车辆种类 */
    private String vehicleType;
    /** 车辆种类名称 */
    private String vehicleTypeName;
    /** 承运合同id */
    private String carriageContractId;
    /** 承运合同 */
    private String carriageContract;
    /** 购销合同ID */
    private String purchaseInfoId;
    /** 购销合同编号 */
    private String purchaseCode;
    /** 派单人 */
    private String singlePayerId;
    /** 派单人 */
    private String singlePayerName;
    /** 派单时间 */
    private Date paymentTime;
    /** 修改人 */
    private String updatePersonId;
    /** 修改人 */
    private String updatePersonName;
    /** 修改时间 */
    private Date updateTime;
    /** 状态：0-未完成 1-已完成 */
    private String status;
    /** 审核人 */
    private String checkerId;
    /** 核对状态0未核对，1已核对 */
    private String checkStatus;
    /** 审核时间 */
    private Date checkTime;
    /** 审核时间 */
    private String createName;
    /** 审核时间 */
    private String createId;
    /** 审核时间 */
    private Date createTime;
    /** 数据状态 */
    private String dataStatu;
    /**所在县*/
	private String countyId;
	/**所在市*/
	private String cityId;
	/**所在县*/
	private String countyCode;
	/**所在市*/
	private String cityCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getServiceRemark() {
        return serviceRemark;
    }

    public void setServiceRemark(String serviceRemark) {
        this.serviceRemark = serviceRemark;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getReceiptPlace() {
        return receiptPlace;
    }

    public void setReceiptPlace(String receiptPlace) {
        this.receiptPlace = receiptPlace;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCarriageContractId() {
        return carriageContractId;
    }

    public void setCarriageContractId(String carriageContractId) {
        this.carriageContractId = carriageContractId;
    }

    public String getCarriageContract() {
        return carriageContract;
    }

    public void setCarriageContract(String carriageContract) {
        this.carriageContract = carriageContract;
    }

    public String getSinglePayerId() {
        return singlePayerId;
    }

    public void setSinglePayerId(String singlePayerId) {
        this.singlePayerId = singlePayerId;
    }

    public String getSinglePayerName() {
        return singlePayerName;
    }

    public void setSinglePayerName(String singlePayerName) {
        this.singlePayerName = singlePayerName;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getUpdatePersonId() {
        return updatePersonId;
    }

    public void setUpdatePersonId(String updatePersonId) {
        this.updatePersonId = updatePersonId;
    }

    public String getUpdatePersonName() {
        return updatePersonName;
    }

    public void setUpdatePersonName(String updatePersonName) {
        this.updatePersonName = updatePersonName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDataStatu() {
        return dataStatu;
    }

    public void setDataStatu(String dataStatu) {
        this.dataStatu = dataStatu;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

	public String getCoalRelatedCode() {
		return coalRelatedCode;
	}

	public void setCoalRelatedCode(String coalRelatedCode) {
		this.coalRelatedCode = coalRelatedCode;
	}

	public String getPurchaseInfoId() {
		return purchaseInfoId;
	}

	public void setPurchaseInfoId(String purchaseInfoId) {
		this.purchaseInfoId = purchaseInfoId;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
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
}

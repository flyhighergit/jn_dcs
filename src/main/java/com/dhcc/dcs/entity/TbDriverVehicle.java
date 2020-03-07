package com.dhcc.dcs.entity;

import java.util.Date;

/**
 * 
 * 司机-车辆-承运单位中间表.
 */
public class TbDriverVehicle {
    /** 主键ID */
    private String id;
    /** 司机ID */
    private String driverId;
    /** 车辆ID */
    private String vehicleId;
    /** 承运企业ID */
    private String companyId;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
    /** 数据状态 默认：1 其他：0*/
    private String status;
    /** 车牌号*/
    private String licensePlate;

    public TbDriverVehicle() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public TbDriverVehicle(String driverId, String companyId, String status) {
        this.driverId = driverId;
        this.companyId = companyId;
        this.status = status;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}

package com.dhcc.dcs.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门机构表
 * 
 * @author
 */

public class TSDepart {
    /** id */
    private String id;
    private TSDepart TSPDepart;// 上级部门
    private String departname;// 部门名称
    private String description;// 部门描述
    private String parentdepartid;// 父部门ID
    private String orgCode;// 机构编码
    private String orgType;// 机构编码
    private String mobile;// 电话
    private String fax;// 传真
    private String address;// 地址
    private String departOrder;// 排序
    private String departnameEn;// 英文名
    private String departnameAbbr;// 缩写
    private String memo;// 备注
    /** 创建人名称 */
    private java.lang.String createName;
    /** 创建人登录名称 */
    private java.lang.String createBy;
    /** 创建日期 */
    private java.util.Date createDate;
    /** 更新人名称 */
    private java.lang.String updateName;
    /** 更新人登录名称 */
    private java.lang.String updateBy;
    /** 更新日期 */
    private java.util.Date updateDate;
    /** 所属部门 */
    private java.lang.String sysOrgCode;
    /** 所属公司 */
    private java.lang.String sysCompanyCode;

    private List<TSDepart> TSDeparts = new ArrayList<TSDepart>();// 下属部门

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TSDepart getTSPDepart() {
        return TSPDepart;
    }

    public void setTSPDepart(TSDepart tSPDepart) {
        TSPDepart = tSPDepart;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentdepartid() {
        return parentdepartid;
    }

    public void setParentdepartid(String parentdepartid) {
        this.parentdepartid = parentdepartid;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartOrder() {
        return departOrder;
    }

    public void setDepartOrder(String departOrder) {
        this.departOrder = departOrder;
    }

    public String getDepartnameEn() {
        return departnameEn;
    }

    public void setDepartnameEn(String departnameEn) {
        this.departnameEn = departnameEn;
    }

    public String getDepartnameAbbr() {
        return departnameAbbr;
    }

    public void setDepartnameAbbr(String departnameAbbr) {
        this.departnameAbbr = departnameAbbr;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public java.lang.String getCreateName() {
        return createName;
    }

    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }

    public java.lang.String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(java.lang.String createBy) {
        this.createBy = createBy;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public java.lang.String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(java.lang.String updateName) {
        this.updateName = updateName;
    }

    public java.lang.String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(java.lang.String updateBy) {
        this.updateBy = updateBy;
    }

    public java.util.Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    public java.lang.String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(java.lang.String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    public java.lang.String getSysCompanyCode() {
        return sysCompanyCode;
    }

    public void setSysCompanyCode(java.lang.String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode;
    }

    public List<TSDepart> getTSDeparts() {
        return TSDeparts;
    }

    public void setTSDeparts(List<TSDepart> tSDeparts) {
        TSDeparts = tSDeparts;
    }

}
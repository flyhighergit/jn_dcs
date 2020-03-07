package com.dhcc.dcs.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 磅秤记录表实体类.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-12 lz
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
public class TbWeighingRecord  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**id*/
	private String id;
	/**公司编号*/
	private String companyCode;
	/**磅秤编码*/
	private String weighbridgeCode;
	/**生成时间*/
	private Date createDate;
	/**重量*/
	private Double weight;
	/**车牌号*/
	private String vehicleCode;
	/**派单id*/
	private String dispatchingId;
	/**状态*/
	private String statu;
	/**状态*/
	private String checkId;
	/**状态*/
	private Date checkDate;
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  id
	 */
	public String getId(){
		return this.id;
	}


	public String getCompanyCode() {
		return companyCode;
	}


	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}


	public String getWeighbridgeCode() {
		return weighbridgeCode;
	}


	public void setWeighbridgeCode(String weighbridgeCode) {
		this.weighbridgeCode = weighbridgeCode;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Double getWeight() {
		return weight;
	}


	public void setWeight(Double weight) {
		this.weight = weight;
	}


	public String getVehicleCode() {
		return vehicleCode;
	}


	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}


	public String getDispatchingId() {
		return dispatchingId;
	}


	public void setDispatchingId(String dispatchingId) {
		this.dispatchingId = dispatchingId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
}

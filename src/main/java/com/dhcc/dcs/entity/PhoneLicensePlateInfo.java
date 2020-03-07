package com.dhcc.dcs.entity;

/**
 * 
 * 设备绑定实体类.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-26 zhanglei
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
public class PhoneLicensePlateInfo {	
	/**设备唯一识别码*/
	private String id;
	/**手机型号*/
	private String model;
	/**手机厂商*/
	private String product;
	/**绑定车牌号*/
	private String licenseplate;
	
	
	/**
	 *方法: 取得String
	 *@return: String  设备唯一识别码
	 */
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置String
	 *@param: String  设备唯一识别码
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  手机型号
	 */
	public String getModel(){
		return this.model;
	}

	/**
	 *方法: 设置String
	 *@param: String  手机型号
	 */
	public void setModel(String model){
		this.model = model;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  手机厂商
	 */
	public String getProduct(){
		return this.product;
	}

	/**
	 *方法: 设置String
	 *@param: String  手机厂商
	 */
	public void setProduct(String product){
		this.product = product;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  绑定车牌号
	 */
	public String getLicenseplate(){
		return this.licenseplate;
	}

	/**
	 *方法: 设置String
	 *@param: String  绑定车牌号
	 */
	public void setLicenseplate(String licenseplate){
		this.licenseplate = licenseplate;
	}
	
	
}

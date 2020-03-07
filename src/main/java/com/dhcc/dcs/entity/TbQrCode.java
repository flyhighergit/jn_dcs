package com.dhcc.dcs.entity;
import java.util.Date;

/**
 * 
 * 二维码实体类.
 * 
 * <pre>
 * 	历史记录：
 * 	2019-11-07 zhanglei
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
public class TbQrCode {
	/**id*/
	private String id;
	/**创建人名称*/
	private String createName;
	/**创建人登录名称*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**更新人名称*/
	private String updateName;
	/**更新人登录名称*/
	private String updateBy;
	/**更新日期*/
	private Date updateDate;
	/**key值*/
	private String keyValue;
	/**公司编码*/
	private String companyCode;
	/**磅房编码*/
	private String bfCode;
	/**生效时间*/
	private Date beginTime;
	/**失效时间*/
	private Date endTime;
	/**状态0历史1当前*/
	private String status;
	/**版本*/
	private String version;
	
	
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
	 *@return: String  创建人登录名称
	 */
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
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
	 *方法: 取得String
	 *@return: String  更新人登录名称
	 */
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  更新人登录名称
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
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
	
	/**
	 *方法: 取得String
	 *@return: String  key值
	 */
	public String getKeyValue(){
		return this.keyValue;
	}

	/**
	 *方法: 设置String
	 *@param: String  key值
	 */
	public void setKeyValue(String keyValue){
		this.keyValue = keyValue;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  公司编码
	 */
	public String getCompanyCode(){
		return this.companyCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  公司编码
	 */
	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  磅房编码
	 */
	public String getBfCode(){
		return this.bfCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  磅房编码
	 */
	public void setBfCode(String bfCode){
		this.bfCode = bfCode;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  生效时间
	 */
	public Date getBeginTime(){
		return this.beginTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  生效时间
	 */
	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  失效时间
	 */
	public Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  失效时间
	 */
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  状态0历史1当前
	 */
	public String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置String
	 *@param: String  状态0历史1当前
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  版本
	 */
	public String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置String
	 *@param: String  版本
	 */
	public void setVersion(String version){
		this.version = version;
	}
	
	
}

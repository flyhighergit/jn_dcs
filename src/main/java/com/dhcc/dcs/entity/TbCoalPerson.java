package com.dhcc.dcs.entity;
import java.util.Date;

/**
 * 
 * 涉煤单位人员关联表实体类.
 * 
 *
 */
public class TbCoalPerson {
	/**id*/
	private String id;
	/**人员id*/
	private String userid;
	/**人员名称*/
	private String username;
	/**涉煤企业id*/
	private String qyId;
	/**企业名称*/
	private String qyName;
	/**涉煤县id*/
	private String xxId;
	/**涉煤县名称*/
	private String xxName;
	/**创建人名称*/
	private String createName;
	/**创建人id*/
	private String createId;
	/**创建日期*/
	private Date createDate;
	/**更新人名称*/
	private String updateName;
	/**更新人id*/
	private String updateId;
	/**更新日期*/
	private Date updateDate;
	
	
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
	 *@return: String  人员id
	 */
	public String getUserid(){
		return this.userid;
	}

	/**
	 *方法: 设置String
	 *@param: String  人员id
	 */
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  人员名称
	 */
	public String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置String
	 *@param: String  人员名称
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  涉煤企业id
	 */
	public String getQyId(){
		return this.qyId;
	}

	/**
	 *方法: 设置String
	 *@param: String  涉煤企业id
	 */
	public void setQyId(String qyId){
		this.qyId = qyId;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  企业名称
	 */
	public String getQyName(){
		return this.qyName;
	}

	/**
	 *方法: 设置String
	 *@param: String  企业名称
	 */
	public void setQyName(String qyName){
		this.qyName = qyName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  涉煤县id
	 */
	public String getXxId(){
		return this.xxId;
	}

	/**
	 *方法: 设置String
	 *@param: String  涉煤县id
	 */
	public void setXxId(String xxId){
		this.xxId = xxId;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  涉煤县名称
	 */
	public String getXxName(){
		return this.xxName;
	}

	/**
	 *方法: 设置String
	 *@param: String  涉煤县名称
	 */
	public void setXxName(String xxName){
		this.xxName = xxName;
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
	 *@return: String  创建人id
	 */
	public String getCreateId(){
		return this.createId;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人id
	 */
	public void setCreateId(String createId){
		this.createId = createId;
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
	 *@return: String  更新人id
	 */
	public String getUpdateId(){
		return this.updateId;
	}

	/**
	 *方法: 设置String
	 *@param: String  更新人id
	 */
	public void setUpdateId(String updateId){
		this.updateId = updateId;
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
	
	
}

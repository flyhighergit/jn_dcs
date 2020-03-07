package com.dhcc.dcs.entity;
import java.util.Date;

/**
 * 
 * 驾驶证附件实体类.(与司机关联，区别于和车主关联的tbAttachmentJsz)
 * 
 */
public class TbDriverJsz {
	
	/**id*/
	private String id;
	/**车辆信息_id*/
	private String driverId;
	/**附件名称*/
	private String attaName;
	/**附件地址*/
	private String attaPath;
	/**上传用户名称*/
	private String ulUserName;
	/**上传用户id*/
	private String ulUserId;
	/**上传时间*/
	private Date ulTime;
	
	
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
	
	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	/**
	 *方法: 取得String
	 *@return: String  附件名称
	 */
	public String getAttaName(){
		return this.attaName;
	}

	/**
	 *方法: 设置String
	 *@param: String  附件名称
	 */
	public void setAttaName(String attaName){
		this.attaName = attaName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  附件地址
	 */
	public String getAttaPath(){
		return this.attaPath;
	}

	/**
	 *方法: 设置String
	 *@param: String  附件地址
	 */
	public void setAttaPath(String attaPath){
		this.attaPath = attaPath;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  上传用户名称
	 */
	public String getUlUserName(){
		return this.ulUserName;
	}

	/**
	 *方法: 设置String
	 *@param: String  上传用户名称
	 */
	public void setUlUserName(String ulUserName){
		this.ulUserName = ulUserName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  上传用户id
	 */
	public String getUlUserId(){
		return this.ulUserId;
	}

	/**
	 *方法: 设置String
	 *@param: String  上传用户id
	 */
	public void setUlUserId(String ulUserId){
		this.ulUserId = ulUserId;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  上传时间
	 */
	public Date getUlTime(){
		return this.ulTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  上传时间
	 */
	public void setUlTime(Date ulTime){
		this.ulTime = ulTime;
	}
}

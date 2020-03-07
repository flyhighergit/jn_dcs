package com.dhcc.dcs.entity;
import java.util.Date;


/**
 * 
 * 车型实体类.
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
public class TbBaseCx {
	
	/**id*/
	private String id;
	/**车型名称*/
	private String cxmc;
	/**车型编码*/
	private String cxbm;
	/**车型轴数*/
	private Integer cxzs;
	/**车型核载*/
	private Double cxhz;
	/**长度*/
	private Double length;
	/**宽度*/
	private Double width;
	/**高度*/
	private Double height;
	/**创建人名称*/
	private String createName;
	/**创建人id*/
	private String createId;
	/**创建时间*/
	private Date createTime;
	/**更新人*/
	private String updateName;
	/**更新人Id*/
	private String updateId;
	/**更新时间*/
	private Date updateTime;
	
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
	 *@return: String  车型名称
	 */
	public String getCxmc(){
		return this.cxmc;
	}

	/**
	 *方法: 设置String
	 *@param: String  车型名称
	 */
	public void setCxmc(String cxmc){
		this.cxmc = cxmc;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车型编码
	 */
	public String getCxbm(){
		return this.cxbm;
	}

	/**
	 *方法: 设置String
	 *@param: String  车型编码
	 */
	public void setCxbm(String cxbm){
		this.cxbm = cxbm;
	}
	
	/**
	 *方法: 取得Integer
	 *@return: Integer  车型轴数
	 */
	public Integer getCxzs(){
		return this.cxzs;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  车型轴数
	 */
	public void setCxzs(Integer cxzs){
		this.cxzs = cxzs;
	}
	
	/**
	 *方法: 取得Double
	 *@return: Double  车型核载
	 */
	public Double getCxhz(){
		return this.cxhz;
	}

	/**
	 *方法: 设置Double
	 *@param: Double  车型核载
	 */
	public void setCxhz(Double cxhz){
		this.cxhz = cxhz;
	}
	
	/**
	 *方法: 取得Double
	 *@return: Double  长度
	 */
	public Double getLength(){
		return this.length;
	}

	/**
	 *方法: 设置Double
	 *@param: Double  长度
	 */
	public void setLength(Double length){
		this.length = length;
	}
	
	/**
	 *方法: 取得Double
	 *@return: Double  宽度
	 */
	public Double getWidth(){
		return this.width;
	}

	/**
	 *方法: 设置Double
	 *@param: Double  宽度
	 */
	public void setWidth(Double width){
		this.width = width;
	}
	
	/**
	 *方法: 取得Double
	 *@return: Double  高度
	 */
	public Double getHeight(){
		return this.height;
	}

	/**
	 *方法: 设置Double
	 *@param: Double  高度
	 */
	public void setHeight(Double height){
		this.height = height;
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
	 *@return: Date  创建时间
	 */
	public Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  创建时间
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  更新人
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置String
	 *@param: String  更新人
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  更新人Id
	 */
	public String getUpdateId(){
		return this.updateId;
	}

	/**
	 *方法: 设置String
	 *@param: String  更新人Id
	 */
	public void setUpdateId(String updateId){
		this.updateId = updateId;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  更新时间
	 */
	public Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  更新时间
	 */
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	
}

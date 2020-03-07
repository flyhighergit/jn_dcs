package com.dhcc.dcs.entity;

import java.util.Date;

/**
 * 
 * 派车单实体类.
 * 
 * <pre>
 * 	历史记录：
 * 	2020-02-19 zhanglei
 * 	新建文件
 * </pre>
 * 
 * @author
 * 
 * <pre>
 * SD
 * 	zhanglei
 * PG
 *	zhanglei
 * UT
 *
 * MA
 * </pre>
 * 
 * @version $Rev$
 *
 * <p/>
 * $Id$
 *
 */
public class TbDispatchBill {	
	/**id*/
	private String id;
	/**编码*/
	private String dispatchAutoCode;
	/**购销合同ID*/
	private String purchaseInfoId;
	/**承运合同ID*/
	private String carriageContractId;
	/**承运单位ID*/
	private String carriageUnitId;
	/**承运单位CODE*/
	private String carriageUnitCode;
	/**承运单位名称*/
	private String carriageUnitName;
	/**派车方式00按次派车，01按协议派车*/
	private String dispatchType;
	/**出卖方ID*/
	private String sellerId;
	/**出卖方名称*/
	private String sellerName;
	/**买受方ID*/
	private String buyerId;
	/**买受方名称*/
	private String buyerName;
	/**起运地公司编码(涉煤企业编码)*/
	private String coalRelatedCode;
	/**起运地*/
	private String deliveryPlace;
	/**目的地*/
	private String receiptPlace;
	/**派车时间*/
	private Date serviceTime;
	/**状态00未签收、01待送货、02运输中、03已完成、04人工作废、05超时作废*/
	private String status;
	/**车牌号*/
	private String licensePlate;
	/**司机名称*/
	private String driverName;
	/**司机id*/
	private String driverId;
	/**签收时间*/
	private Date signTime;
	/**创建人ID*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**更新者ID*/
	private String updateBy;
	/**更新日期*/
	private Date updateDate;
	/**备用字段1*/
	private String attribute1;
	/**备用字段2*/
	private String attribute2;
	/**备用字段3*/
	private String attribute3;
	/**备用字段4*/
	private String attribute4;
	/**备用字段5*/
	private String attribute5;
	/**备用字段6*/
	private String attribute6;
	/**备用字段7*/
	private String attribute7;
	/**备用字段8*/
	private String attribute8;
	/**备用字段9*/
	private String attribute9;
	/**备用字段10*/
	private String attribute10;
	/**煤种 */
	private String goodsTypeName;
	/**品种 */
	private String goodsVarietyName;
	
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
	 *@return: String  编码
	 */
	public String getDispatchAutoCode(){
		return this.dispatchAutoCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  编码
	 */
	public void setDispatchAutoCode(String dispatchAutoCode){
		this.dispatchAutoCode = dispatchAutoCode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  购销合同ID
	 */
	public String getPurchaseInfoId(){
		return this.purchaseInfoId;
	}

	/**
	 *方法: 设置String
	 *@param: String  购销合同ID
	 */
	public void setPurchaseInfoId(String purchaseInfoId){
		this.purchaseInfoId = purchaseInfoId;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  承运合同ID
	 */
	public String getCarriageContractId(){
		return this.carriageContractId;
	}

	/**
	 *方法: 设置String
	 *@param: String  承运合同ID
	 */
	public void setCarriageContractId(String carriageContractId){
		this.carriageContractId = carriageContractId;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  承运单位ID
	 */
	public String getCarriageUnitId(){
		return this.carriageUnitId;
	}

	/**
	 *方法: 设置String
	 *@param: String  承运单位ID
	 */
	public void setCarriageUnitId(String carriageUnitId){
		this.carriageUnitId = carriageUnitId;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  承运单位CODE
	 */
	public String getCarriageUnitCode(){
		return this.carriageUnitCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  承运单位CODE
	 */
	public void setCarriageUnitCode(String carriageUnitCode){
		this.carriageUnitCode = carriageUnitCode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  承运单位名称
	 */
	public String getCarriageUnitName(){
		return this.carriageUnitName;
	}

	/**
	 *方法: 设置String
	 *@param: String  承运单位名称
	 */
	public void setCarriageUnitName(String carriageUnitName){
		this.carriageUnitName = carriageUnitName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  派车方式00按次派车，01按协议派车
	 */
	public String getDispatchType(){
		return this.dispatchType;
	}

	/**
	 *方法: 设置String
	 *@param: String  派车方式00按次派车，01按协议派车
	 */
	public void setDispatchType(String dispatchType){
		this.dispatchType = dispatchType;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  出卖方ID
	 */
	public String getSellerId(){
		return this.sellerId;
	}

	/**
	 *方法: 设置String
	 *@param: String  出卖方ID
	 */
	public void setSellerId(String sellerId){
		this.sellerId = sellerId;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  出卖方名称
	 */
	public String getSellerName(){
		return this.sellerName;
	}

	/**
	 *方法: 设置String
	 *@param: String  出卖方名称
	 */
	public void setSellerName(String sellerName){
		this.sellerName = sellerName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  买受方ID
	 */
	public String getBuyerId(){
		return this.buyerId;
	}

	/**
	 *方法: 设置String
	 *@param: String  买受方ID
	 */
	public void setBuyerId(String buyerId){
		this.buyerId = buyerId;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  买受方名称
	 */
	public String getBuyerName(){
		return this.buyerName;
	}

	/**
	 *方法: 设置String
	 *@param: String  买受方名称
	 */
	public void setBuyerName(String buyerName){
		this.buyerName = buyerName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  起运地公司编码(涉煤企业编码)
	 */
	public String getCoalRelatedCode(){
		return this.coalRelatedCode;
	}

	/**
	 *方法: 设置String
	 *@param: String  起运地公司编码(涉煤企业编码)
	 */
	public void setCoalRelatedCode(String coalRelatedCode){
		this.coalRelatedCode = coalRelatedCode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  起运地
	 */
	public String getDeliveryPlace(){
		return this.deliveryPlace;
	}

	/**
	 *方法: 设置String
	 *@param: String  起运地
	 */
	public void setDeliveryPlace(String deliveryPlace){
		this.deliveryPlace = deliveryPlace;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  目的地
	 */
	public String getReceiptPlace(){
		return this.receiptPlace;
	}

	/**
	 *方法: 设置String
	 *@param: String  目的地
	 */
	public void setReceiptPlace(String receiptPlace){
		this.receiptPlace = receiptPlace;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  派车时间
	 */
	public Date getServiceTime(){
		return this.serviceTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  派车时间
	 */
	public void setServiceTime(Date serviceTime){
		this.serviceTime = serviceTime;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  状态00未签收、01待送货、02运输中、03已完成、04人工作废、05超时作废
	 */
	public String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置String
	 *@param: String  状态00未签收、01待送货、02运输中、03已完成、04人工作废、05超时作废
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  车牌号
	 */
	public String getLicensePlate(){
		return this.licensePlate;
	}

	/**
	 *方法: 设置String
	 *@param: String  车牌号
	 */
	public void setLicensePlate(String licensePlate){
		this.licensePlate = licensePlate;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  司机名称
	 */
	public String getDriverName(){
		return this.driverName;
	}

	/**
	 *方法: 设置String
	 *@param: String  司机名称
	 */
	public void setDriverName(String driverName){
		this.driverName = driverName;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  司机id
	 */
	public String getDriverId(){
		return this.driverId;
	}

	/**
	 *方法: 设置String
	 *@param: String  司机id
	 */
	public void setDriverId(String driverId){
		this.driverId = driverId;
	}
	
	/**
	 *方法: 取得Date
	 *@return: Date  签收时间
	 */
	public Date getSignTime(){
		return this.signTime;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  签收时间
	 */
	public void setSignTime(Date signTime){
		this.signTime = signTime;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  创建人ID
	 */
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人ID
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
	 *@return: String  更新者ID
	 */
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  更新者ID
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
	 *@return: String  备用字段1
	 */
	public String getAttribute1(){
		return this.attribute1;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段1
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段2
	 */
	public String getAttribute2(){
		return this.attribute2;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段2
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段3
	 */
	public String getAttribute3(){
		return this.attribute3;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段3
	 */
	public void setAttribute3(String attribute3){
		this.attribute3 = attribute3;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段4
	 */
	public String getAttribute4(){
		return this.attribute4;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段4
	 */
	public void setAttribute4(String attribute4){
		this.attribute4 = attribute4;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段5
	 */
	public String getAttribute5(){
		return this.attribute5;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段5
	 */
	public void setAttribute5(String attribute5){
		this.attribute5 = attribute5;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段6
	 */
	public String getAttribute6(){
		return this.attribute6;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段6
	 */
	public void setAttribute6(String attribute6){
		this.attribute6 = attribute6;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段7
	 */
	public String getAttribute7(){
		return this.attribute7;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段7
	 */
	public void setAttribute7(String attribute7){
		this.attribute7 = attribute7;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段8
	 */
	public String getAttribute8(){
		return this.attribute8;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段8
	 */
	public void setAttribute8(String attribute8){
		this.attribute8 = attribute8;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段9
	 */
	public String getAttribute9(){
		return this.attribute9;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段9
	 */
	public void setAttribute9(String attribute9){
		this.attribute9 = attribute9;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  备用字段10
	 */
	public String getAttribute10(){
		return this.attribute10;
	}

	/**
	 *方法: 设置String
	 *@param: String  备用字段10
	 */
	public void setAttribute10(String attribute10){
		this.attribute10 = attribute10;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public String getGoodsVarietyName() {
		return goodsVarietyName;
	}

	public void setGoodsVarietyName(String goodsVarietyName) {
		this.goodsVarietyName = goodsVarietyName;
	}
	
	
}

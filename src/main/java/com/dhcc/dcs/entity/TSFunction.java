package com.dhcc.dcs.entity;

/**
 * 系统用户父类表
 * 
 * @author
 */

public class TSFunction {

	
	private String id;//菜单ID
	private String parentFunctionId;//父菜单ID
	private String functionName;//菜单名称
	private Short functionLevel;//菜单等级
	private String functionUrl;//菜单地址
	private Short functionLoadMode;//菜单对应url加载方式，分重新加载和不重新加载（只做隐藏、展现操作）
	private Short functionIframe;//菜单地址打开方式
	private String functionOrder;//菜单排序
	private Short functionType;//菜单类型
	private String functionIconStyle;//菜单图标样式
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentFunctionId() {
		return parentFunctionId;
	}
	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public Short getFunctionLevel() {
		return functionLevel;
	}
	public void setFunctionLevel(Short functionLevel) {
		this.functionLevel = functionLevel;
	}
	public String getFunctionUrl() {
		return functionUrl;
	}
	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}
	public Short getFunctionLoadMode() {
		return functionLoadMode;
	}
	public void setFunctionLoadMode(Short functionLoadMode) {
		this.functionLoadMode = functionLoadMode;
	}
	public Short getFunctionIframe() {
		return functionIframe;
	}
	public void setFunctionIframe(Short functionIframe) {
		this.functionIframe = functionIframe;
	}
	public String getFunctionOrder() {
		return functionOrder;
	}
	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}
	public Short getFunctionType() {
		return functionType;
	}
	public void setFunctionType(Short functionType) {
		this.functionType = functionType;
	}
	public String getFunctionIconStyle() {
		return functionIconStyle;
	}
	public void setFunctionIconStyle(String functionIconStyle) {
		this.functionIconStyle = functionIconStyle;
	}
}
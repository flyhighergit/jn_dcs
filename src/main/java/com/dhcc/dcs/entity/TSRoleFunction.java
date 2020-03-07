package com.dhcc.dcs.entity;

/**
 * 系统用户父类表
 * 
 * @author
 */

public class TSRoleFunction {

	private TSFunction TSFunction;
	private TSRole TSRole;
	private String operation;
	private String dataRule;
	public TSFunction getTSFunction() {
		return TSFunction;
	}
	public void setTSFunction(TSFunction tSFunction) {
		TSFunction = tSFunction;
	}
	public TSRole getTSRole() {
		return TSRole;
	}
	public void setTSRole(TSRole tSRole) {
		TSRole = tSRole;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getDataRule() {
		return dataRule;
	}
	public void setDataRule(String dataRule) {
		this.dataRule = dataRule;
	}
}
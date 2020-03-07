package com.dhcc.dcs.dto;

import java.util.List;
import java.util.Map;

import com.dhcc.dcs.enums.StateEnum;

public class Execution<T> {
	// 结果状态
	private int state;
	
	// 状态标识
	private String stateInfo;
	
	// 影响行数
	private int count;
	
	// 操作的对象
	private T t;

	// 查询结果列表
	private List<T> list;
	
	// 查询结果map
	private Map<String,Object> map;
	
	public Execution() {
		
	}
	
	// 操作失败的时候使用的构造器
	public Execution(StateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功的时候使用的构造器
	public Execution(StateEnum stateEnum, T t) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.t = t;
	}
	
	// 操作成功的时候使用的构造器
	public Execution(StateEnum stateEnum, List<T> list) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.list = list;
	}

	public Execution(StateEnum stateEnum, Map<String,Object> map) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.map = map;
	}
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}

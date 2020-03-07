package com.dhcc.dcs.enums;

/**
 * 使用枚举表述常量数据字典
 */
public enum StateEnum {

	SUCCESS(0, "操作成功"), CHECK(1, "审核中"),INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "信息为空"),
	DRAFT(3,"保存草稿"),REJECT(2,"退回");
	
	private int state;
	
	private String stateInfo;

	private StateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static StateEnum stateOf(int index) {
		for (StateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
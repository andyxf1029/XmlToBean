package com.ebao.gs.integration.framework.configuration.cust.bean;

public class ConditionBean {

	private String id;

	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ConditionBean [id=" + id + ", value=" + value + "]";
	}

}

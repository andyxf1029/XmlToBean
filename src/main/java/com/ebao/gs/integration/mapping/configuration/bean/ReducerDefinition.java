package com.ebao.gs.integration.mapping.configuration.bean;

public class ReducerDefinition {

	private String id;

	private String className;

	private String rulePath;

	public String getRulePath() {
		return rulePath;
	}

	public void setRulePath(String rulePath) {
		this.rulePath = rulePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "ReducerDefinition [id=" + id + ", className=" + className
				+ ", rulePath=" + rulePath + "]";
	}
}

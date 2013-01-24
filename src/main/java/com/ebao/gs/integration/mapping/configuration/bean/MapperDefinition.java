package com.ebao.gs.integration.mapping.configuration.bean;

public class MapperDefinition {

	private String id;

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "MapperDefinition [id=" + id + ", className=" + className + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

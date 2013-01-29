package com.ebao.gs.integration.mapping.bean.impl;

import java.util.Map;

import com.ebao.gs.integration.mapping.bean.InputDataSource;

public class InputXMLDataSource<String> implements InputDataSource {

	private String content;

	private Map<String, Object> extMap;

	public InputXMLDataSource(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public Map<String, Object> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, Object> extMap) {
		this.extMap = extMap;
	}

}

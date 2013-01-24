package com.ebao.gs.integration.mapping.bean.impl;

import com.ebao.gs.integration.mapping.bean.InputDataSource;

public class InputXMLDataSource<String> implements InputDataSource {

	private String content;

	public InputXMLDataSource(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}

package com.ebao.gs.integration.mapping.bean.impl;

import com.ebao.gs.integration.mapping.bean.OutputDataSource;

public class OutputDataBeanSource implements OutputDataSource {

	private Object content;

	public Object getContent() {
		return this.content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}

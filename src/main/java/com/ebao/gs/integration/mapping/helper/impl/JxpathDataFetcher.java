package com.ebao.gs.integration.mapping.helper.impl;

import org.apache.commons.jxpath.JXPathContext;

import com.ebao.gs.integration.mapping.aop.Cacheable;
import com.ebao.gs.integration.mapping.helper.IDataFetcher;

public class JxpathDataFetcher implements IDataFetcher {

	
	public String getValue(Object value, String acordPath) {
		return (String) this.createBeanContext(value).getValue(acordPath);
	}

	public JXPathContext createBeanContext(Object targetBean) {
		JXPathContext context = JXPathContext.newContext(targetBean);
		context.setLenient(true);
		return context;
	}
}

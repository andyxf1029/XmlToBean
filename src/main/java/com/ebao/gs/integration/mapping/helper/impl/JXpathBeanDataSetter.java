package com.ebao.gs.integration.mapping.helper.impl;

import org.apache.commons.jxpath.JXPathContext;

import com.ebao.gs.integration.mapping.helper.IBeanDataSetter;

public class JXpathBeanDataSetter implements IBeanDataSetter<JXPathContext> {

	public void setValue(JXPathContext targetBeanContext, String beanPath,
			Object soureValue) {
		targetBeanContext.setValue(beanPath, soureValue);
	}

	public JXPathContext createBeanContext(Object targetBean) {
		return JXPathContext.newContext(targetBean);
	}
}

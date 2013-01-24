package com.ebao.gs.integration.mapping.helper;

import org.apache.commons.jxpath.JXPathContext;

public interface IBeanDataSetter<T> {

	void setValue(T targetBeanContext, String beanPath, Object soureValue);

	JXPathContext createBeanContext(Object targetBean);

}

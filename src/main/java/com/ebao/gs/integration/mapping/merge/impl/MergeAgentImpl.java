package com.ebao.gs.integration.mapping.merge.impl;

import java.io.IOException;
import java.util.Map;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.configuration.IEngineConfigurationProvider;
import com.ebao.gs.integration.mapping.configuration.bean.MergeBean;
import com.ebao.gs.integration.mapping.helper.IBeanProvider;
import com.ebao.gs.integration.mapping.merge.MergeAgent;
import com.ebao.gs.integration.mapping.merge.Merger;

public class MergeAgentImpl implements MergeAgent {

	private IBeanProvider beanProvider;

	private IEngineConfigurationProvider provider;

	public Object merge(Map<String, Object> resultMap) throws IOException,
			SAXException, InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		MergeBean bean = this.provider.loadMerger();
		Merger meger = this.beanProvider.fetch(bean.getClassName(),
				Merger.class);
		return meger.merge(resultMap);
	}

	public void setBeanProvider(IBeanProvider beanProvider) {
		this.beanProvider = beanProvider;
	}

	public void setProvider(IEngineConfigurationProvider provider) {
		this.provider = provider;
	}
}

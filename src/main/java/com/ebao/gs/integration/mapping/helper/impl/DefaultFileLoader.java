package com.ebao.gs.integration.mapping.helper.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.framework.configuration.cust.IConfigurationCustProvider;
import com.ebao.gs.integration.mapping.helper.IFileLoader;

public class DefaultFileLoader implements IFileLoader {

	private IConfigurationCustProvider custProvider;

	public InputStream load(String name, String path,
			Map<String, String> conditionMap) throws IOException, SAXException,
			TransformerException {
		return custProvider
				.mergeConfigurationCustInfo(name, path, conditionMap);
	}

	public void setCustProvider(IConfigurationCustProvider custProvider) {
		this.custProvider = custProvider;
	}

}

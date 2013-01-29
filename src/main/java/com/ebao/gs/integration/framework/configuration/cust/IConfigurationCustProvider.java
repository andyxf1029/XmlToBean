package com.ebao.gs.integration.framework.configuration.cust;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public interface IConfigurationCustProvider {

	InputStream mergeConfigurationCustInfo(String fileName, String filePath,
			Map<String, String> conditionMap) throws IOException, SAXException,
			TransformerException;

}

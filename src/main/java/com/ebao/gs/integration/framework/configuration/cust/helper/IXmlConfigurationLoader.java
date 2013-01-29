package com.ebao.gs.integration.framework.configuration.cust.helper;

import java.io.IOException;
import java.util.Map;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.framework.configuration.cust.bean.RuleSet;

public interface IXmlConfigurationLoader {

	public RuleSet loadConfigurationBeanByCondition(String fileName,
			Map<String, String> conditionMap) throws IOException, SAXException;

}

package com.ebao.gs.integration.framework.configuration.cust;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.framework.configuration.cust.helper.IXmlConfigurationLoader;
import com.ebao.gs.integration.framework.configuration.cust.helper.impl.XMLConfigurationLoader;

public class XMLConfigurationLoaderTest {

	@Test
	public void testTwoCondition() throws IOException, SAXException {

		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("BinessUnitCode", "55");
		conditionMap.put("ProgramCode", "103");
		IXmlConfigurationLoader loader = new XMLConfigurationLoader();
		System.out.println(loader.loadConfigurationBeanByCondition(
				"Ct-mapping.rule", conditionMap));
	}

	//
	// @Test
	// public void tesCondition() throws IOException, SAXException {
	// Condition condition = new Condition();
	// condition.buildConditionMap("1", "55");
	// IXmlConfigurationLoader loader = new XMLConfigurationLoader();
	// Rule rule = loader.loadMergeRuleByFileName("22", condition);
	// Assert.assertTrue(rule == null);
	// }

	@Test
	public void loadALlConfiguration() throws IOException, SAXException {

		XMLConfigurationLoader loader = new XMLConfigurationLoader();

		System.out.println(loader.loadConfigurationBean());

	}

}

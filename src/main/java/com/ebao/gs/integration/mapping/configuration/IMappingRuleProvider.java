package com.ebao.gs.integration.mapping.configuration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.configuration.bean.RuleSet;

public interface IMappingRuleProvider {

	List<RuleSet> loadRuleSetByKey(String key) throws IOException, SAXException;

	List<RuleSet> loadRuleSetByFilePath(String path) throws IOException,
			SAXException;

	List<RuleSet> loadAllRuleSetPath() throws IOException, SAXException;

	List<RuleSet> loadRuleSetByKey(String key, Map<String, String> conditionMap)
			throws IOException, SAXException, TransformerException;

}

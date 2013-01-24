package com.ebao.gs.integration.mapping.configuration.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.aop.Cacheable;
import com.ebao.gs.integration.mapping.configuration.IMappingRuleProvider;
import com.ebao.gs.integration.mapping.configuration.IMultipleFileReader;
import com.ebao.gs.integration.mapping.configuration.bean.Rule;
import com.ebao.gs.integration.mapping.configuration.bean.RuleSet;
import com.ebao.gs.integration.mapping.configuration.bean.Rules;

/**
 * 
 * @author FY
 * 
 */
public class MappingRuleProvider implements IMappingRuleProvider {

	private IMultipleFileReader multipleFileReader;

	private String folderName;

	@Cacheable(key="file")
	private Rules loadAllRuleSet() throws IOException, SAXException {
		Collection fileList = multipleFileReader
				.loadRulesByFolderName(folderName);
		Rules resultBean = new Rules();
		for (Object object : fileList) {
			File file = (File) object;
			this.mergeRuleSet(resultBean, this.getRules(file));
		}

		return resultBean;

	}

	private void mergeRuleSet(Rules resultBean, Rules bean) {
		resultBean.addRuleSetList(bean.getRuleSetList());
	}

	private Rules getRules(File file) throws IOException, SAXException {
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("rules", Rules.class);
		digester.addObjectCreate("rules/ruleSet", RuleSet.class);
		digester.addObjectCreate("rules/ruleSet/rule", Rule.class);
		digester.addBeanPropertySetter("rules/ruleSet/rule/acord-path",
				"acordPath");
		digester.addBeanPropertySetter("rules/ruleSet/rule/bean-path",
				"beanPath");
		digester.addBeanPropertySetter("rules/ruleSet/rule/tools", "tool");
		digester.addSetProperties("rules/ruleSet/rule", "path", "path");
		digester.addSetProperties("rules/ruleSet/rule", "ref", "ref");
		digester.addSetProperties("rules/ruleSet/rule", "after", "after");
		digester.addBeanPropertySetter("rules/ruleSet/rule/special-rule",
				"specialRule");
		digester.addSetNext("rules/ruleSet/rule", "addRule");
		digester.addSetProperties("rules/ruleSet", "id", "id");
		digester.addSetProperties("rules/ruleSet", "createBean",
				"createBeanName");
		digester.addSetNext("rules/ruleSet", "addRuleSet");
		Rules bean = digester.parse(file);
		return bean;
	}

	@Cacheable(key="rules")
	public List<RuleSet> loadRuleSetByKey(String key) throws IOException,
			SAXException {
		List<RuleSet> result = new ArrayList<RuleSet>();
		List<RuleSet> configurationRuleSetList = this.loadAllRuleSet()
				.getRuleSetList();
		for (RuleSet ruleSet : configurationRuleSetList) {
			if (key.equals(ruleSet.getId())) {
				result.add(ruleSet);
			}
		}
		return result;
	}

	public List<RuleSet> loadRuleSetByFilePath(String path) throws IOException,
			SAXException {
		return this.loadAllRuleSet().getRuleSetList();
	}

	public void setMultipleFileReader(IMultipleFileReader multipleFileReader) {
		this.multipleFileReader = multipleFileReader;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

}

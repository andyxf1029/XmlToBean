package com.ebao.gs.integration.mapping.configuration.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.framework.aop.Cacheable;
import com.ebao.gs.integration.framework.common.IBasePathProvider;
import com.ebao.gs.integration.mapping.configuration.IMappingRuleProvider;
import com.ebao.gs.integration.mapping.configuration.IMultipleFileReader;
import com.ebao.gs.integration.mapping.configuration.bean.Rule;
import com.ebao.gs.integration.mapping.configuration.bean.RuleSet;
import com.ebao.gs.integration.mapping.configuration.bean.Rules;
import com.ebao.gs.integration.mapping.helper.IFileLoader;
import com.ebao.gs.integration.mapping.utils.ParameterUtils;

/**
 * 
 * @author FY
 * 
 */
public class MappingRuleProvider implements IMappingRuleProvider {

	private IMultipleFileReader multipleFileReader;

	private String folderName;

	private IFileLoader fileLoader;

	private IBasePathProvider basePathProvider;

	@Cacheable(key = "file")
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

	@Cacheable(key = "ruleSet")
	public List<RuleSet> loadRuleSetByKey(String key,
			Map<String, String> conditionMap) throws IOException, SAXException,
			TransformerException {
		List<RuleSet> result = new ArrayList<RuleSet>();
		List<RuleSet> configurationRuleSetList = this.loadAllRuleSet(
				conditionMap).getRuleSetList();
		for (RuleSet ruleSet : configurationRuleSetList) {
			if (key.equals(ruleSet.getId())) {
				result.add(ruleSet);
			}
		}
		return result;
	}

	private Rules loadAllRuleSet(Map<String, String> conditionMap)
			throws IOException, SAXException, TransformerException {
		Collection fileList = multipleFileReader
				.loadRulesByFolderName(folderName);
		Rules resultBean = new Rules();
		for (Object object : fileList) {
			File file = (File) object;
			InputStream source = this.fileLoader.load(file.getName(),
					ParameterUtils.buildFilePath(
							this.basePathProvider.getBasePath(),
							this.folderName), conditionMap);
			this.mergeRuleSet(resultBean, this.getRules(source));
		}

		return resultBean;
	}

	private Rules getRules(InputStream source) throws IOException, SAXException {
		Digester digester = this.buildDegister();
		Rules bean = digester.parse(source);
		return bean;
	}

	private Digester buildDegister() {
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("rules", Rules.class);
		digester.addObjectCreate("rules/ruleSet", RuleSet.class);
		digester.addObjectCreate("rules/ruleSet/rule", Rule.class);
		digester.addBeanPropertySetter("rules/ruleSet/rule/acord-path",
				"acordPath");
		digester.addBeanPropertySetter("rules/ruleSet/rule/bean-path",
				"beanPath");
		digester.addBeanPropertySetter("rules/ruleSet/rule/default-value",
				"defaultValue");
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
		digester.addSetProperties("rules/ruleSet", "path", "path");
		digester.addSetProperties("rules/ruleSet", "context", "context");
		digester.addSetNext("rules/ruleSet", "addRuleSet");
		return digester;
	}

	public List<RuleSet> loadAllRuleSetPath() throws IOException, SAXException {
		Rules rules = this.loadAllRuleSet();
		List<RuleSet> resultRuleSet = new ArrayList<RuleSet>();
		for (RuleSet ruleSet : rules.getRuleSetList()) {
			if (StringUtils.isNotBlank(ruleSet.getPath())) {
				resultRuleSet.add(ruleSet);
			}
		}

		return resultRuleSet;
	}

	private void mergeRuleSet(Rules resultBean, Rules bean) {
		resultBean.addRuleSetList(bean.getRuleSetList());
	}

	private Rules getRules(File file) throws IOException, SAXException {
		Digester digester = this.buildDegister();
		Rules bean = digester.parse(file);
		return bean;
	}

	@Cacheable(key = "rules")
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

	public void setFileLoader(IFileLoader fileLoader) {
		this.fileLoader = fileLoader;
	}

	public void setBasePathProvider(IBasePathProvider basePathProvider) {
		this.basePathProvider = basePathProvider;
	}

}

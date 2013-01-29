package com.ebao.gs.integration.framework.configuration.cust.helper.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester3.Digester;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.framework.configuration.cust.bean.ConditionBean;
import com.ebao.gs.integration.framework.configuration.cust.bean.ConfigurationCustBean;
import com.ebao.gs.integration.framework.configuration.cust.bean.Rule;
import com.ebao.gs.integration.framework.configuration.cust.bean.RuleSet;
import com.ebao.gs.integration.framework.configuration.cust.helper.IXmlConfigurationLoader;

public class XMLConfigurationLoader implements IXmlConfigurationLoader {

	private static final String RESOURCE_CUST_CONFIG_XML = "resource/cust_config.xml";
	private static final String ALL = "*";

	public RuleSet loadConfigurationBeanByCondition(String fileName,
			Map<String, String> conditionMap) throws IOException, SAXException {

		ConfigurationCustBean custBean = this.loadConfigurationBean();
		List<RuleSet> resultList = new ArrayList<RuleSet>();
		for (RuleSet ruleSet : custBean.getRuleSetList()) {
			this.findRuleSet(fileName, conditionMap, resultList, ruleSet);
		}

		Assert.isTrue(!(resultList.size() > 1),
				"response ruleSet size >1  pls check cust-configuration file , result is "
						+ resultList);

		return resultList.isEmpty() ? null : resultList.get(0);
	}

	private void findRuleSet(String fileName, Map<String, String> conditionMap,
			List<RuleSet> resultList, RuleSet ruleSet) {
		if (ruleSet.getFileName().equals(fileName)) {
			if (checkCondition(conditionMap, ruleSet.getConditionList())) {
				resultList.add(ruleSet);
			}
		}
	}

	private boolean checkCondition(Map<String, String> conditionMap,
			List<ConditionBean> list) {
		boolean flag = true;
		for (ConditionBean conditionBean : list) {
			if (conditionMap.containsKey(conditionBean.getId())
					&& conditionBean.getValue().equals(ALL)) {
				continue;
			}

			if (!conditionMap.containsKey(conditionBean.getId())) {
				flag = false;
			}

			if (conditionMap.containsKey(conditionBean.getId())
					&& !this.isSame(conditionMap, conditionBean)) {
				flag = false;
			}

		}

		return flag;

	}

	private boolean isSame(Map<String, String> conditionMap,
			ConditionBean conditionBean) {
		String[] valueList = conditionBean.getValue().split(",");
		return ArrayUtils.contains(valueList,
				conditionMap.get(conditionBean.getId()));
	}

	public ConfigurationCustBean loadConfigurationBean() throws IOException,
			SAXException {
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("configuration-cust",
				ConfigurationCustBean.class);
		digester.addObjectCreate("configuration-cust/ruleSet", RuleSet.class);
		digester.addObjectCreate("configuration-cust/ruleSet/rule", Rule.class);
		digester.addObjectCreate(
				"configuration-cust/ruleSet/conditions/condition",
				ConditionBean.class);
		digester.addBeanPropertySetter("configuration-cust/ruleSet/fileName",
				"fileName");
		digester.addBeanPropertySetter(
				"configuration-cust/ruleSet/rule/targetPath", "targetPath");
		digester.addBeanPropertySetter(
				"configuration-cust/ruleSet/rule/replaceValue", "replaceValue");
		digester.addSetProperties(
				"configuration-cust/ruleSet/conditions/condition", "id", "id");
		digester.addBeanPropertySetter(
				"configuration-cust/ruleSet/conditions/condition", "value");
		digester.addSetNext("configuration-cust/ruleSet/conditions/condition",
				"addCondition");
		digester.addSetNext("configuration-cust/ruleSet/rule", "addRule");
		digester.addSetNext("configuration-cust/ruleSet", "addRuleSet");
		ConfigurationCustBean bean = digester.parse(new File(
				RESOURCE_CUST_CONFIG_XML));
		return bean;
	}
}

package com.ebao.gs.integration.framework.configuration.cust.bean;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationCustBean {

	private List<RuleSet> ruleSetList = new ArrayList<RuleSet>();

	

	public void addRuleSet(RuleSet ruleSet) {
		this.ruleSetList.add(ruleSet);
	}

	

	@Override
	public String toString() {
		return "ConfigurationCustBean [ruleSetList=" + ruleSetList + "]";
	}

	public List<RuleSet> getRuleSetList() {
		return ruleSetList;
	}

	

}

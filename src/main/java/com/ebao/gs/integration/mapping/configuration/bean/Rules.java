package com.ebao.gs.integration.mapping.configuration.bean;

import java.util.ArrayList;
import java.util.List;

public class Rules {

	private List<RuleSet> ruleSetList = new ArrayList<RuleSet>();

	public void addRuleSet(RuleSet ruleSet) {
		this.ruleSetList.add(ruleSet);
	}

	public List<RuleSet> getRuleSetList() {
		return ruleSetList;
	}

	public void addRuleSetList(List<RuleSet> ruleList) {
		this.ruleSetList.addAll(ruleList);
	}

	@Override
	public String toString() {
		return "Rules [ruleSetList=" + ruleSetList + "]";
	}

}

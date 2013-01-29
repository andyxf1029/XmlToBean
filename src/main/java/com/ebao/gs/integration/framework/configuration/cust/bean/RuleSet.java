package com.ebao.gs.integration.framework.configuration.cust.bean;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {

	private String fileName;

	private List<Rule> rules = new ArrayList<Rule>();

	private List<ConditionBean> conditionList = new ArrayList<ConditionBean>();

	public void addCondition(ConditionBean condition) {
		this.conditionList.add(condition);
	}

	public List<ConditionBean> getConditionList() {
		return conditionList;
	}

	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	@Override
	public String toString() {
		return "RuleSet [fileName=" + fileName + ", rules=" + rules
				+ ", conditionList=" + conditionList + "]";
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public List<Rule> getRules() {
		return rules;
	}

}

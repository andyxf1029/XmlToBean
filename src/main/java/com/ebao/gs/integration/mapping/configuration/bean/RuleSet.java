package com.ebao.gs.integration.mapping.configuration.bean;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {

	private String id;

	private String createBeanName;

	private String path;

	private List<Rule> rules = new ArrayList<Rule>();

	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBeanName() {
		return createBeanName;
	}

	public void setCreateBeanName(String createBeanName) {
		this.createBeanName = createBeanName;
	}

	public List<Rule> getRules() {
		return rules;
	}

	@Override
	public String toString() {
		return "RuleSet [id=" + id + ", createBeanName=" + createBeanName
				+ ", path=" + path + ", rules=" + rules + "]";
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

package com.ebao.gs.integration.mapping.configuration.bean;

public class Rule {

	private String path;

	private String ref;

	private String acordPath;

	private String beanPath;

	private String tool;

	private String after;

	private String specialRule;

	public String getAcordPath() {
		return acordPath;
	}

	public void setAcordPath(String acordPath) {
		this.acordPath = acordPath;
	}

	public String getBeanPath() {
		return beanPath;
	}

	public void setBeanPath(String beanPath) {
		this.beanPath = beanPath;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getSpecialRule() {
		return specialRule;
	}

	public void setSpecialRule(String specialRule) {
		this.specialRule = specialRule;
	}

	@Override
	public String toString() {
		return "Rule [path=" + path + ", ref=" + ref + ", acordPath="
				+ acordPath + ", beanPath=" + beanPath + ", tool=" + tool
				+ ", specialRule=" + specialRule + "]";
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

}

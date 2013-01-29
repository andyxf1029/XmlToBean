package com.ebao.gs.integration.framework.configuration.cust.bean;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	private String targetPath;

	private String replaceValue;

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getReplaceValue() {
		return replaceValue;
	}

	public void setReplaceValue(String replaceValue) {
		this.replaceValue = replaceValue;
	}

	@Override
	public String toString() {
		return "Rule [targetPath=" + targetPath + ", replaceValue="
				+ replaceValue + "]";
	}
}

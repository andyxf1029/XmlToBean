package com.ebao.gs.integration.framework.configuration.cust.bean;

import java.util.HashMap;
import java.util.Map;

public class Condition {

	private Map<String, String> conditionMap = new HashMap<String, String>();

	public Condition buildConditionMap(String key, String value) {
		conditionMap.put(key, value);
		return this;
	}

	public boolean containKey(String key) {
		return this.conditionMap.containsKey(key);
	}

	public String getValue(String key) {
		return this.conditionMap.get(key);
	}

}

package com.ebao.gs.integration.mapping.rule.impl;

import java.util.Map;

import com.ebao.gs.integration.mapping.rule.ISpecialRule;

public class DefaultSpecialRule implements ISpecialRule {

	public Object call(Object value, Map<String, String> contextMap) {
		System.out.println(value);
		System.out.println(contextMap);
		// TODO Auto-generated method stub
		return null;
	}

}

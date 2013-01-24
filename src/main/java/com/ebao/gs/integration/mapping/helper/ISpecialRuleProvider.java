package com.ebao.gs.integration.mapping.helper;

public interface ISpecialRuleProvider {

	public Object call(String rule, Object value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException;

}

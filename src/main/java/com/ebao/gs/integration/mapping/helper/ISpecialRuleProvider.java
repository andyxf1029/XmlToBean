package com.ebao.gs.integration.mapping.helper;

import java.io.IOException;

public interface ISpecialRuleProvider {

	public Object call(String rule, Object value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException;

}

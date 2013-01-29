package com.ebao.gs.integration.mapping.rule;

import java.util.Map;

public interface ISpecialRule {

	public Object call(Object value, Map<String, String> contextMap);
}

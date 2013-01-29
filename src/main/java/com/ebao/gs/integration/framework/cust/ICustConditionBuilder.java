package com.ebao.gs.integration.framework.cust;

import java.util.Map;

import com.ebao.gs.integration.mapping.bean.InputDataSource;

public interface ICustConditionBuilder {

	public Map<String, String> build(InputDataSource input);
}

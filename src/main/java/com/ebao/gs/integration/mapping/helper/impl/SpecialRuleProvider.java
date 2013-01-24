package com.ebao.gs.integration.mapping.helper.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.ebao.gs.integration.mapping.helper.ISpecialRuleProvider;

public class SpecialRuleProvider extends BeanHelper implements
		ISpecialRuleProvider {

	public Object call(String ruleName, Object value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Assert.notNull(ruleName,
				"call special Rule  ,parameter ruleName is null");
		return super.call(ruleName, value);
	}

	/**
	 * Customization findMethod can support different signature rule service
	 */
	public Method findMethod(Map<String, String> parametersMap,
			Object toolsInstance) {
		Method method = ReflectionUtils.findMethod(toolsInstance.getClass(),
				parametersMap.get(METHOD), new Class[] { Object.class,
						Map.class });
		return method;
	}

}

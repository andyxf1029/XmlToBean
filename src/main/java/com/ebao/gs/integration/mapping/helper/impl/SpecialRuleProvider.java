package com.ebao.gs.integration.mapping.helper.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import clojure.lang.RT;
import clojure.lang.Var;

import com.ebao.gs.integration.mapping.helper.ISpecialRuleProvider;
import com.ebao.gs.integration.mapping.utils.BeanTypeUtils;
import com.ebao.gs.integration.mapping.utils.ParameterUtils;

public class SpecialRuleProvider extends BeanHelper implements
		ISpecialRuleProvider {

	private static final String DEFAULT_NAME_SPACE = "com.ebao.gs.integration.mapping";
	private String cljPath;

	public void setCljPath(String cljPath) {
		this.cljPath = cljPath;
	}

	public Object call(String ruleName, Object value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException {
		Assert.notNull(ruleName,
				"call special Rule  ,parameter ruleName is null");
		if (BeanTypeUtils.isClojuire(ruleName)) {
			String beanName = StringUtils.substringBetween(ruleName, ":", "?");
			RT.loadResourceScript(this.buildClojurePath(this.cljPath, beanName));
			Map<String, String> paramMap = ParameterUtils
					.getParameters(ruleName);
			String method = paramMap.get(METHOD);
			Var var = RT.var(DEFAULT_NAME_SPACE, method);
			return var.invoke(value, paramMap);

		}

		return super.run(ruleName, value);
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

	protected String buildClojurePath(String cljPath, String beanName) {
		return cljPath + File.separator + beanName + ".clj";
	}

}

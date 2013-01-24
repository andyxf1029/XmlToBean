package com.ebao.gs.integration.mapping.helper.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import com.ebao.gs.integration.mapping.helper.constants.BeanType;
import com.ebao.gs.integration.mapping.utils.BeanTypeUtils;

public abstract class BeanHelper implements ApplicationContextAware {
	
	
	public static final String METHOD = "method";
	public static final String CODE_TABLE = "codeTable";
	public static final String PRE_CONDITION = "preCondition";

	protected ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public Object call(String tool, Object value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Map<String, String> parametersMap = this.getParameters(tool);
		Object toolsInstance = this.getInstance(tool);
		Object[] args = this.buildArgs(value, parametersMap);
		Method method = this.findMethod(parametersMap, toolsInstance);
		return ReflectionUtils.invokeMethod(method, toolsInstance, args);
	}

	abstract Method findMethod(Map<String, String> parametersMap,
			Object toolsInstance);

	protected Map<String, String> getParameters(String toolName) {
		int index = StringUtils.indexOf(toolName, "?");
		if (index > 0) {
			Map<String, String> parametersMap = new HashMap<String, String>();
			String parameters = StringUtils.substringAfter(toolName, "?");
			String[] parameterList = StringUtils.split(parameters, "@");
			for (String parameterPair : parameterList) {
				String[] parameter = StringUtils.split(parameterPair, "=");
				if (parameter.length > 0) {
					parametersMap.put(parameter[0], parameter[1]);
				}
			}
			return parametersMap;
		}

		return null;
	}

	protected Object getInstance(String tool) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		String beanName = StringUtils.substringBetween(tool, ":", "?");
		if (BeanTypeUtils.isSpringBean(tool)) {
			return applicationContext.getBean(beanName);
		} else if (BeanType.ClassBean.equals(BeanTypeUtils
				.getBeanType(beanName))) {
			return Class.forName(beanName).newInstance();
		}

		return null;
	}

	protected Object[] buildArgs(Object value, Map<String, String> parametersMap) {
		return new Object[] { value, parametersMap };
	}

}

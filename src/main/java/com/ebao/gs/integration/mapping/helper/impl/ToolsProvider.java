package com.ebao.gs.integration.mapping.helper.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.ebao.gs.integration.mapping.helper.IToolsProiver;

public class ToolsProvider extends BeanHelper implements IToolsProiver {

	public Object call(String tool, Object value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Assert.notNull(tool, "call Tools ,parameter toolname is null");
		return super.run(tool, value);
	}

	public Method findMethod(Map<String, String> parametersMap,
			Object toolsInstance) {
		Method method = ReflectionUtils.findMethod(toolsInstance.getClass(),
				parametersMap.get(METHOD), new Class[] { Object.class,
						Map.class });
		return method;
	}


}

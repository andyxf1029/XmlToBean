package com.ebao.gs.integration.mapping.reduce.after.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.ebao.gs.integration.mapping.reduce.after.IAfterAction;

public class BeanArrayListUtil {

	public static void addList(Object targetBean, Object resultBean, Object fieldName) {
		Assert.notNull(fieldName, "add List  method is null");
		Assert.notNull(targetBean, "add List  targetBean is null");
		Assert.notNull(resultBean, "add List  resultBean is null");
		String name = String.valueOf(fieldName);
		String getMethodName = "get"
				+ StringUtils.substring(name, 0, 1).toUpperCase()+StringUtils.substring(name, 1);
		Method getMethod = ReflectionUtils.findMethod(targetBean.getClass(),
				getMethodName);
		Assert.notNull(getMethod, "add List , can't found method ");
		Object result = ReflectionUtils.invokeMethod(getMethod, targetBean);
		if (result == null) {
			List emptyList = new ArrayList();
			String setMethodName = "set"
					+ StringUtils.substring(name, 0, 1).toUpperCase()+StringUtils.substring(name, 1);
			emptyList.addAll((List) resultBean);
			Method setMethod = ReflectionUtils.findMethod(
					targetBean.getClass(), setMethodName);
			ReflectionUtils.invokeMethod(setMethod, targetBean,
					new Object[] { emptyList });
		} else {
			List list = (List) result;
			list.addAll((List) resultBean);
		}
	}
}

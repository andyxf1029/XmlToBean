package com.ebao.gs.integration.mapping.utils;

import com.ebao.gs.integration.mapping.helper.constants.BeanType;

public class BeanTypeUtils {

	public static boolean isClojuire(String beanName) {
		return beanName.startsWith("clj:");
	}

	public static boolean isSpringBean(String beanName) {
		return beanName.startsWith("bean:");
	}

	public static BeanType getBeanType(String beanName) {
		if (BeanTypeUtils.isClojuire(beanName)) {
			return BeanType.Clojure;
		} else if (BeanTypeUtils.isSpringBean(beanName)) {
			return BeanType.SpringBean;
		} else {
			return BeanType.ClassBean;
		}

	}
}

package com.ebao.gs.integration.mapping.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ParameterUtils {

	public static Map<String, String> getParameters(String toolName) {
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

	public static String buildFilePath(String path, String fileName) {
		if (StringUtils.isBlank(path)) {
			return fileName;
		}

		return path + File.separator + fileName;

	}

}

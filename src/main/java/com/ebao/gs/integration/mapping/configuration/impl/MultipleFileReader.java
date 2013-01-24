package com.ebao.gs.integration.mapping.configuration.impl;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.ebao.gs.integration.framework.common.IBasePathProvider;
import com.ebao.gs.integration.mapping.configuration.IMultipleFileReader;
import com.ebao.gs.integration.mapping.configuration.bean.Rules;

public class MultipleFileReader implements IMultipleFileReader {

	private IBasePathProvider basePathProvider;

	public Collection loadRulesByFolderName(String folderName) {
		Assert.notNull(folderName, "loadRulesByFolderName   folderName is null");
		File folder = new File(this.getFolderPath(folderName));
		Collection list = FileUtils.listFiles(folder, this.getExtDefination(),
				false);
		return list;
	}

	private String[] getExtDefination() {
		String[] extList = { "rule" };
		return extList;
	}

	private String getFolderPath(String folderName) {
		String basePath = basePathProvider.getBasePath();
		if (StringUtils.isBlank(basePath)) {
			return folderName;
		}

		return basePath + File.separator + folderName;
	}

	public void setBasePathProvider(IBasePathProvider basePathProvider) {
		this.basePathProvider = basePathProvider;
	}
}

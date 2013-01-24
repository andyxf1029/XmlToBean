package com.ebao.gs.integration.core.mapping.ds;

import org.junit.Test;

import com.ebao.gs.integration.mapping.configuration.impl.MultipleFileReader;

public class MultipleRuleFileReaderTest {

	@Test
	public void test() {
		MultipleFileReader reader = new MultipleFileReader();
		reader.loadRulesByFolderName("resource");
	}

}

package com.ebao.gs.integration.core.mapping.ds;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ebao.gs.integration.mapping.helper.ISpecialRuleProvider;

public class SpecialRuleTest {

	@Test
	public void test() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				"resource/xmlToBeanEngine.xml");
		ISpecialRuleProvider provider = (ISpecialRuleProvider) ac
				.getBean("SpecialRuleProvider");
		provider.call("clj:argo?method=test", "1");
	}

}

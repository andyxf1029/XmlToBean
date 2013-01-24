package com.ebao.gs.integration.core.mapping.ds;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.framework.common.impl.BasePathDefaultProvider;
import com.ebao.gs.integration.mapping.configuration.IMappingRuleProvider;
import com.ebao.gs.integration.mapping.configuration.bean.RuleSet;
import com.ebao.gs.integration.mapping.configuration.impl.MappingRuleProvider;
import com.ebao.gs.integration.mapping.configuration.impl.MultipleFileReader;

public class MappingRuleProviderTest {

	@Test
	public void test() throws IOException, SAXException {

		ApplicationContext ac = new FileSystemXmlApplicationContext(
				"resource/xmlToBeanEngine.xml");
		IMappingRuleProvider provider = (IMappingRuleProvider) ac
				.getBean("MappingRuleProvider");
		MultipleFileReader reader = new MultipleFileReader();
		reader.setBasePathProvider(new BasePathDefaultProvider());
		// provider.setMultipleFileReader(reader);
		// provider.setFolderName("resource");
		Object ruleList = provider.loadRuleSetByKey("coverage");
		System.out.println(ruleList);
		Object ruleList2 = provider.loadRuleSetByKey("Classification");
		System.out.println(ruleList2);
	}
}

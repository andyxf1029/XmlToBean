package com.ebao.gs.integration.core.mapping.ds;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.bean.impl.InputXMLDataSource;
import com.ebao.gs.integration.mapping.configuration.impl.MappingRuleProvider;
import com.ebao.gs.integration.mapping.helper.impl.JXpathBeanDataSetter;
import com.ebao.gs.integration.mapping.helper.impl.JxpathDataFetcher;
import com.ebao.gs.integration.mapping.helper.impl.SpecialRuleProvider;
import com.ebao.gs.integration.mapping.mapper.impl.DefaultMapper;
import com.ebao.gs.integration.mapping.reduce.DefaultReducer;

public class DefaultReducerTest {

	@Test
	public void test() throws IOException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, SAXException {
		String xml = FileUtils.readFileToString(new File(
				"src/test/java/testData1.xml"));
		DefaultMapper mapper = new DefaultMapper();
		InputXMLDataSource<String> xmlData = new InputXMLDataSource<String>(xml);
		List<Pair> pairList = mapper.map(xmlData);
		for (Pair pair : pairList) {
			System.out.println(pair);
			DefaultReducer reducer = new DefaultReducer();
			reducer.setRulePorvider(new MappingRuleProvider());
			reducer.setBeanSetter(new JXpathBeanDataSetter());
			reducer.setDataFetcher(new JxpathDataFetcher());
			reducer.setSpecialRuleProvider(new SpecialRuleProvider());
			Map<String, Object> resultMap = reducer.reduce(pair, new HashMap());
			System.out.println(resultMap);

		}

	}
}

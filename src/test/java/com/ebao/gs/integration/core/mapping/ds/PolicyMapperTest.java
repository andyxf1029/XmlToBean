package com.ebao.gs.integration.core.mapping.ds;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.bean.impl.InputXMLDataSource;
import com.ebao.gs.integration.mapping.mapper.impl.DefaultMapper;

public class PolicyMapperTest {

	@Test
	public void test() throws IOException, SAXException {

		String xml = FileUtils.readFileToString(new File(
				"src/test/java/testData1.xml"));

		DefaultMapper mapper = new DefaultMapper();

		InputXMLDataSource<String> xmlData = new InputXMLDataSource<String>(xml);

		List<Pair> pairList = mapper.map(xmlData);

		System.out.println(pairList);

		// Assert.isTrue(pairList.size() == 2);
		// Assert.isTrue(pairList.get(0).getValues().size() == 3);
		// load XML

		// call mapper

		// assert

	}
	
	@Test
	public void testCust() throws IOException, SAXException {

		String xml = FileUtils.readFileToString(new File(
				"src/test/java/testData1.xml"));

		DefaultMapper mapper = new DefaultMapper();

		InputXMLDataSource<String> xmlData = new InputXMLDataSource<String>(xml);

		List<Pair> pairList = mapper.map(xmlData);

		System.out.println(pairList);

		// Assert.isTrue(pairList.size() == 2);
		// Assert.isTrue(pairList.get(0).getValues().size() == 3);
		// load XML

		// call mapper

		// assert

	}
}

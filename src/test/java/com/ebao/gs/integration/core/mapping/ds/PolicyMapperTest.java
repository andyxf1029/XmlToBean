package com.ebao.gs.integration.core.mapping.ds;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ebao.as.gs.integration.mapping.mapper.PolicyMapper;
import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.bean.impl.InputXMLDataSource;

public class PolicyMapperTest {

	@Test
	public void test() throws IOException {

		String xml = FileUtils.readFileToString(new File(
				"src/test/java/testData1.xml"));

		PolicyMapper mapper = new PolicyMapper();

		InputXMLDataSource<String> xmlData = new InputXMLDataSource<String>(xml);

		List<Pair> pairList = mapper.map(xmlData);
		
		System.out.println(pairList);

//		Assert.isTrue(pairList.size() == 2);
//		Assert.isTrue(pairList.get(0).getValues().size() == 3);
		// load XML

		// call mapper

		// assert

	}
}

package com.ebao.gs.integration.core.mapping.ds;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.util.Assert;
import org.xml.sax.SAXException;

import com.ebao.as.integration.acord.bean.PolicySOABO;
import com.ebao.gs.integration.mapping.IXMLToBeanEngine;
import com.ebao.gs.integration.mapping.bean.InputDataSource;
import com.ebao.gs.integration.mapping.bean.OutputDataSource;
import com.ebao.gs.integration.mapping.bean.impl.InputXMLDataSource;
import com.ebao.gs.integration.mapping.impl.XMLToBeanEnigneImpl;

public class TestMappingEgine {

	@Test
	public void call_mapping_return_String() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException,
			SAXException {

		String xml = FileUtils.readFileToString(new File(
				"src/test/java/testData1.xml"));

		ApplicationContext ac = new FileSystemXmlApplicationContext(
				"resource/xmlToBeanEngine.xml");

		// // 1 . get engine from spring
		// IMappingEngine engine = new MappingEngine();
		IXMLToBeanEngine engine = (IXMLToBeanEngine) ac
				.getBean("XMLToBeanEnigne");
		InputDataSource source = new InputXMLDataSource(xml);
		PolicySOABO output = engine.process(source, PolicySOABO.class);
		PolicySOABO output2 = engine.process(source, PolicySOABO.class);
		// Assert.isInstanceOf(String.class, output.getContent());

		System.out.println(output);
	}

}

package com.ebao.gs.integration.mapping;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.InputDataSource;

public interface IXMLToBeanEngine {

	public <T> T process(InputDataSource source, Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException;
}

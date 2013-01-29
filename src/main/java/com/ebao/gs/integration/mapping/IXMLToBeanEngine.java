package com.ebao.gs.integration.mapping;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.framework.cust.ICustConditionBuilder;
import com.ebao.gs.integration.mapping.bean.InputDataSource;

public interface IXMLToBeanEngine {

	public <T> T process(InputDataSource source, Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException;

	public <T> T process(InputDataSource source, Class<T> clazz,
			ICustConditionBuilder builder) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException,
			SAXException, TransformerException;
}

package com.ebao.gs.integration.mapping.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public interface IFileLoader {

	public InputStream load(String name, String path,
			Map<String, String> conditionMap) throws IOException, SAXException,
			TransformerException;
}

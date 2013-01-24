package com.ebao.gs.integration.mapping.merge;

import java.io.IOException;
import java.util.Map;

import org.xml.sax.SAXException;

public interface MergeAgent {

	public Object merge(Map<String, Object> resultMap) throws IOException,
			SAXException, InstantiationException, IllegalAccessException, ClassNotFoundException;
}

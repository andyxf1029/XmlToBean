package com.ebao.as.gs.integration.mapping.mapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.xml.JDOMParser;

import com.ebao.gs.integration.mapping.bean.InputDataSource;
import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.bean.impl.InputXMLDataSource;
import com.ebao.gs.integration.mapping.mapper.Mapper;

public class PolicyMapper implements Mapper {

	// TODO add configuration file to make this configurable
	private static Map<String, String> splitPathMap = new HashMap<String, String>();

	static {
		splitPathMap.put("commonPolicy", "Policy/CommonPolicy");
		splitPathMap.put("commonPolicy2", "Policy/CommonPolicy");
		splitPathMap.put("loaction", "Policy/location");
		splitPathMap.put("Classification", "Policy/classifiction");
	}

	public List<Pair> map(InputDataSource source) throws IOException {
		List<Pair> pairList = new ArrayList<Pair>();
		if (source instanceof InputXMLDataSource) {
			this.splitXMLData(source, pairList);
		}

		return pairList;
	}

	private void splitXMLData(InputDataSource source, List<Pair> pairList)
			throws IOException {
		String xmlData = (String) source.getContent();
		JDOMParser parser = new JDOMParser();
		ByteArrayInputStream input = new ByteArrayInputStream(
				xmlData.getBytes());
		try {
			Object dom = parser.parseXML(input);
			JXPathContext context = JXPathContext.newContext(dom);
			for (Entry<String, String> entry : splitPathMap.entrySet()) {
				List<Object> elementList = context
						.selectNodes(entry.getValue());
				pairList.add(new Pair(entry.getKey(), elementList));
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
}

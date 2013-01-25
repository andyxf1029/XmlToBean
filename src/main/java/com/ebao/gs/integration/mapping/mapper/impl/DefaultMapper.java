package com.ebao.gs.integration.mapping.mapper.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.xml.JDOMParser;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.InputDataSource;
import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.bean.impl.InputXMLDataSource;
import com.ebao.gs.integration.mapping.configuration.IMappingRuleProvider;
import com.ebao.gs.integration.mapping.configuration.bean.RuleSet;
import com.ebao.gs.integration.mapping.mapper.Mapper;

public class DefaultMapper implements Mapper {

	private IMappingRuleProvider rulePorvider;

	public List<Pair> map(InputDataSource source) throws IOException,
			SAXException {
		List<Pair> pairList = new ArrayList<Pair>();
		if (source instanceof InputXMLDataSource) {
			this.splitXMLData(source, pairList);
		}

		return pairList;
	}

	private void splitXMLData(InputDataSource source, List<Pair> pairList)
			throws IOException, SAXException {
		String xmlData = (String) source.getContent();
		JDOMParser parser = new JDOMParser();
		ByteArrayInputStream input = new ByteArrayInputStream(
				xmlData.getBytes());
		List<RuleSet> ruleSetList = this.rulePorvider.loadAllRuleSetPath();

		try {
			Object dom = parser.parseXML(input);
			JXPathContext context = JXPathContext.newContext(dom);
			for (RuleSet ruleSet : ruleSetList) {
				List<Object> elementList = context.selectNodes(ruleSet
						.getPath());
				pairList.add(new Pair(ruleSet.getId(), elementList));
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	public void setRulePorvider(IMappingRuleProvider rulePorvider) {
		this.rulePorvider = rulePorvider;
	}

}

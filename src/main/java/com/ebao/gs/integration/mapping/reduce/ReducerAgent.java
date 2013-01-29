package com.ebao.gs.integration.mapping.reduce;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.Pair;

public interface ReducerAgent {

	public Map<String, Object> process(List<Pair> pairs)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException;

	public Map<String, Object> process(List<Pair> mapperpairList,
			Map<String, String> conditionMap) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException,
			SAXException, TransformerException;

}
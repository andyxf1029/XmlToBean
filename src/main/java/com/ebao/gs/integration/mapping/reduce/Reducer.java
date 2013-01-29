package com.ebao.gs.integration.mapping.reduce;

import java.io.IOException;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.Pair;

public interface Reducer {

	public abstract Map<String, Object> reduce(Pair pair, Map<String, Object> contextMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException;

	public abstract Map<? extends String, ? extends Object> reduce(Pair pair,
			Map<String, Object> contextMap, Map<String, String> conditionMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException,
			TransformerException;

}
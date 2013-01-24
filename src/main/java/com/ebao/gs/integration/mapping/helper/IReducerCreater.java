package com.ebao.gs.integration.mapping.helper;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.reduce.Reducer;

public interface IReducerCreater {

	public Reducer createReducer(String key) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException, SAXException;
}

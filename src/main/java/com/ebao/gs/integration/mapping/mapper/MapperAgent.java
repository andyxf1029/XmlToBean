package com.ebao.gs.integration.mapping.mapper;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.InputDataSource;
import com.ebao.gs.integration.mapping.bean.Pair;

public interface MapperAgent {

	List<Pair> processMapper(InputDataSource source)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException;

}

package com.ebao.gs.integration.mapping.mapper;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.InputDataSource;
import com.ebao.gs.integration.mapping.bean.Pair;

public interface Mapper {

	public List<Pair> map(InputDataSource source) throws IOException,
			SAXException;

}

package com.ebao.gs.integration.core.mapping.ds;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.configuration.impl.EngineConfigurationProvider;

public class EngineConfigurationProviderTest {

	@Test
	public void testLoadALl() throws IOException, SAXException {
		EngineConfigurationProvider provider = new EngineConfigurationProvider();
		System.out.println(provider.loadAllConfigurationFile());
	}

	@Test
	public void testLoadMapper() throws IOException, SAXException {
		EngineConfigurationProvider provider = new EngineConfigurationProvider();
		System.out.println(provider.loadMapperByKey("root"));
	}

	@Test
	public void testloadAllMappers() throws IOException, SAXException {
		EngineConfigurationProvider provider = new EngineConfigurationProvider();
		System.out.println(provider.loadMappers());
	}

	@Test
	public void testloadAllReducer() throws IOException, SAXException {
		EngineConfigurationProvider provider = new EngineConfigurationProvider();
		System.out.println(provider.loadReducers());
	}

	@Test
	public void testloadReducerByKey() throws IOException, SAXException {
		EngineConfigurationProvider provider = new EngineConfigurationProvider();
		System.out.println(provider.loadReducerByKey("LOB"));
	}

	@Test
	public void testloadMerge() throws IOException, SAXException {
		EngineConfigurationProvider provider = new EngineConfigurationProvider();
		System.out.println(provider.loadMerger());

	}
}

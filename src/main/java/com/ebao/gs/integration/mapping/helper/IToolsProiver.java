package com.ebao.gs.integration.mapping.helper;

public interface IToolsProiver {


	public Object call(String tool, Object value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException;

}

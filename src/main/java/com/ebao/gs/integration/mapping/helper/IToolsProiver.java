package com.ebao.gs.integration.mapping.helper;

import java.io.IOException;

public interface IToolsProiver {


	public Object call(String tool, Object value)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException;

}

package com.ebao.gs.integration.core.mapping.ds;

import org.junit.Test;

import com.ebao.gs.integration.mapping.helper.impl.ToolsProvider;

public class ToolsProviderTest {

	@Test
	public void test() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String toolsName = "class:com.ebao.gs.integration.mapping.tools.CodeTableUtils?method=format@codeTable=111";
		ToolsProvider tools = new ToolsProvider();
		tools.call(toolsName, "1");
	}
}

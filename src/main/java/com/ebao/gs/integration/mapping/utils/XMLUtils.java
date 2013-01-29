package com.ebao.gs.integration.mapping.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class XMLUtils {

	public static String domToString(Element element) throws IOException {
		XMLOutputter output = new XMLOutputter();
		StringWriter sw = new StringWriter();
		output.output(element, sw);
		return sw.toString();
	}

}

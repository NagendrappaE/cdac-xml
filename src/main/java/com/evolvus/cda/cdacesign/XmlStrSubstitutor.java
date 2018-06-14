package com.evolvus.cda.cdacesign;

import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang.text.StrLookup;
import org.apache.commons.lang.text.StrSubstitutor;

public class XmlStrSubstitutor extends StrSubstitutor {

	public XmlStrSubstitutor(Map valueMap) {
		super(valueMap);
	}

	@Override
	public String resolveVariable(String variableName, StrBuilder buf,
			int startPos, int endPos) {
		StrLookup resolver = getVariableResolver();
		if (resolver == null) {
			return null;
		}
		return StringEscapeUtils.escapeXml(resolver.lookup(variableName));
	}
}



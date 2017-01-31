package org.davidespadini.mockextractor.utils;

import java.util.HashSet;
import java.util.Set;

public class ParserUtil {

    public static String cleanGenerics(String type) {
    	if(!type.contains("<")) return cleanArrays(type);
    	return cleanArrays(type.substring(0, type.indexOf("<")));
	}

	private static String cleanArrays(String type) {
		return type.replace("[]", "");
	}
	

	private static Set<String> primitivesList = new HashSet<String>();
	static {
		primitivesList.add("byte");
		primitivesList.add("int");
		primitivesList.add("long");
		primitivesList.add("short");
		primitivesList.add("float");
		primitivesList.add("double");
		primitivesList.add("boolean");
		primitivesList.add("char");
	}
	
	public static boolean nameIsPrimitive(String type) {
		return primitivesList.contains(type);
	}
}

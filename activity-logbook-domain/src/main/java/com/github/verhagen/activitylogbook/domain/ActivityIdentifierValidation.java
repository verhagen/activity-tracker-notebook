package com.github.verhagen.activitylogbook.domain;

import java.util.regex.Pattern;

public class ActivityIdentifierValidation {
	private static final String REG_EXP_IDENTIFIER = "[a-zA-Z0-9-]+";
	private static Pattern identifierPattern = Pattern.compile("^(" +  REG_EXP_IDENTIFIER + ")$");
	private static Pattern identifiersPattern = Pattern.compile("^(" +  REG_EXP_IDENTIFIER + "(\\." + REG_EXP_IDENTIFIER +")*" + ")$");


	public boolean isValid(String identifier) {
		if (identifier.contains(".")) {
			return isValidPathIdentifier(identifier);
		}
		return isValidIdentifier(identifier);
	}

	public boolean isValidIdentifier(String identifier) {
		if (identifierPattern.matcher(identifier).matches()) {
			return true;
		}
		return false;
	}

	public boolean isValidPathIdentifier(String pathIdentifier) {
		if (identifiersPattern.matcher(pathIdentifier).matches()) {
			return true;
		}
		return false;
	}


	public static IllegalArgumentException createIllegalArgumentException(String argumentName, String argumentValue) {
		return new IllegalArgumentException(createMessage(argumentName, argumentValue));

	}
	public static String createMessage(String argumentName, String argumentValue) {
		return "Argument '" + argumentName + "' with value '" + argumentValue
				+ "' is not a correct identifier. It should follow the regular expression '" + identifierPattern + "'.";
	}

}

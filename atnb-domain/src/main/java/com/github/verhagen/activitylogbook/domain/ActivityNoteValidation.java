package com.github.verhagen.activitylogbook.domain;

import java.util.regex.Pattern;

public class ActivityNoteValidation {
	private Pattern notePattern = Pattern.compile("^\\(.*\\)$");

	boolean isValid(String identifier) {
		if (notePattern.matcher(identifier).matches()) {
			return true;
		}
		return false;
	}
	
}

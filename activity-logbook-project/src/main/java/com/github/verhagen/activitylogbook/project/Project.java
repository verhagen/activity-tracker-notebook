package com.github.verhagen.activitylogbook.project;

import com.github.verhagen.activitylogbook.domain.ActivityIdentifier;
import com.github.verhagen.activitylogbook.domain.ActivityIdentifierValidation;

public class Project implements ActivityIdentifier {
	private final String identifier;

	
	public Project(Builder bldr) {
		this.identifier = bldr.getIdentifier();
	}


	@Override
	public String getIdentifier() {
		return identifier;
	}


	public static class Builder {
		private String identifier;

		public Project create() {
			return new Project(this);
		}

		public Builder add(String identifier) {
			if (! new ActivityIdentifierValidation().isValidIdentifier(identifier)) {
				ActivityIdentifierValidation.createIllegalArgumentException("identifier", identifier);
			}
			this.identifier = identifier;
			return this;
		}

		public String getIdentifier() {
			return identifier;
		}
	}

}

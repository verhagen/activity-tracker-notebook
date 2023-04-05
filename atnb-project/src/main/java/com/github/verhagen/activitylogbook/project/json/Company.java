package com.github.verhagen.activitylogbook.project.json;

import com.github.verhagen.activitylogbook.domain.IdentifierSupplier;
import com.github.verhagen.activitylogbook.domain.Organisation;

public class Company extends Organisation<Project> implements IdentifierSupplier<String> {

	@Override
	public String getIdentifierType() {
		return Company.class.getSimpleName().toLowerCase();
	}

}

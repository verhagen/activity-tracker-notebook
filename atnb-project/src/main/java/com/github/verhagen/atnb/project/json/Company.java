package com.github.verhagen.atnb.project.json;

import com.github.verhagen.atnb.domain.IdentifierSupplier;
import com.github.verhagen.atnb.domain.Organisation;

public class Company extends Organisation<Project> implements IdentifierSupplier<String> {

	@Override
	public String getIdentifierType() {
		return Company.class.getSimpleName().toLowerCase();
	}

}

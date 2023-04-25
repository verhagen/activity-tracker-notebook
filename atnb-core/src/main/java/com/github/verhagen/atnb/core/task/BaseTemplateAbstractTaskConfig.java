package com.github.verhagen.atnb.core.task;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;

public class BaseTemplateAbstractTaskConfig extends TemplateAbstractTaskConfig {

	public BaseTemplateAbstractTaskConfig(IdentifierCatalog idCatalog) {
		super(idCatalog, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		return null;
	}

}

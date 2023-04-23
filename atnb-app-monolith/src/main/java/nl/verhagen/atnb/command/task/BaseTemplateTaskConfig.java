package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.TextFieldExtractor;

public class BaseTemplateTaskConfig extends TemplateTaskConfig {

	public BaseTemplateTaskConfig(IdentifierCatalog idCatalog) {
		super(idCatalog, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		return null;
	}

}

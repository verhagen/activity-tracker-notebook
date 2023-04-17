package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.TextFieldExtractor;

public class BaseTemplateTaskConfiguration extends TemplateTaskConfiguration {

	public BaseTemplateTaskConfiguration(IdentifierCatalog idCatalog) {
		super(idCatalog, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		return null;
	}

}

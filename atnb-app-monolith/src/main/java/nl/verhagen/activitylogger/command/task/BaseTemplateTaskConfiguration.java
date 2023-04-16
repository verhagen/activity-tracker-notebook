package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.IdentifierCatalog;
import nl.verhagen.activitylogger.command.TextFieldExtractor;

public class BaseTemplateTaskConfiguration extends TemplateTaskConfiguration {

	public BaseTemplateTaskConfiguration(IdentifierCatalog idCatalog) {
		super(idCatalog, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		return null;
	}

}

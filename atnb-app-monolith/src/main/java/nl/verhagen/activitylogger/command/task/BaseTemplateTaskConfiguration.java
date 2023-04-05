package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.IdentifierRegistry;
import nl.verhagen.activitylogger.command.TextFieldExtractor;

public class BaseTemplateTaskConfiguration extends TemplateTaskConfiguration {


	public BaseTemplateTaskConfiguration(IdentifierRegistry idRegistery) {
		super(idRegistery, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		
		return null;
	}

}

package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.IdentifierRegistery;
import nl.verhagen.activitylogger.command.TextFieldExtractor;

public class BaseTemplateTaskConfiguration extends TemplateTaskConfiguration {


	public BaseTemplateTaskConfiguration(IdentifierRegistery idRegistery) {
		super(idRegistery, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		
		return null;
	}

}

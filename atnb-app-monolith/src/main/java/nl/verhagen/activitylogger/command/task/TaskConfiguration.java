package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.IdentifierRegistry;
import nl.verhagen.activitylogger.command.TextFieldExtractor;

public abstract class TaskConfiguration {
	private final IdentifierRegistry idRegistry;
	private final TextFieldExtractor textFieldExtractor;

	
	public TaskConfiguration(IdentifierRegistry idRegistry) {
		this(idRegistry, null);
	}
	public TaskConfiguration(IdentifierRegistry idRegistry, TextFieldExtractor textFieldExtractor) {
		if (idRegistry == null) {
			throw new IllegalArgumentException("Argument 'idRegistry' should not be null.");
		}
		this.idRegistry = idRegistry;
		this.textFieldExtractor = textFieldExtractor;
	}


	public IdentifierRegistry getIdRegistry() {
		return idRegistry;
	}


	public TextFieldExtractor getTextFieldExtractor() {
		return textFieldExtractor;
	}

}

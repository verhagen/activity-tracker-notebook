package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.IdentifierRegistry;
import nl.verhagen.activitylogger.command.TextFieldExtractor;

public class RepositoryTaskConfiguration extends TaskConfiguration {

	public RepositoryTaskConfiguration(IdentifierRegistry idRegistry) {
		super(idRegistry);
	}

	public RepositoryTaskConfiguration(IdentifierRegistry idRegistry, TextFieldExtractor textFieldExtractor) {
		super(idRegistry, textFieldExtractor);
	}

}

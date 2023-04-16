package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.IdentifierCatalog;
import nl.verhagen.activitylogger.command.TextFieldExtractor;

public class RepositoryTaskConfiguration extends TaskConfiguration {

	public RepositoryTaskConfiguration(IdentifierCatalog idRegistry) {
		super(idRegistry);
	}

	public RepositoryTaskConfiguration(IdentifierCatalog idRegistry, TextFieldExtractor textFieldExtractor) {
		super(idRegistry, textFieldExtractor);
	}

}

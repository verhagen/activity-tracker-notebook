package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.IdentifierRegistery;
import nl.verhagen.activitylogger.command.TextFieldExtractor;

public class RepositoryTaskConfiguration extends TaskConfiguration {

	public RepositoryTaskConfiguration(IdentifierRegistery idRegistery) {
		super(idRegistery);
	}

	public RepositoryTaskConfiguration(IdentifierRegistery idRegistery, TextFieldExtractor textFieldExtractor) {
		super(idRegistery, textFieldExtractor);
	}

}

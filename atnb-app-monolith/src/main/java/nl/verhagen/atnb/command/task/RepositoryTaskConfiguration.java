package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.TextFieldExtractor;

public class RepositoryTaskConfiguration extends TaskConfiguration {

	public RepositoryTaskConfiguration(IdentifierCatalog idRegistry) {
		super(idRegistry);
	}

	public RepositoryTaskConfiguration(IdentifierCatalog idRegistry, TextFieldExtractor textFieldExtractor) {
		super(idRegistry, textFieldExtractor);
	}

}

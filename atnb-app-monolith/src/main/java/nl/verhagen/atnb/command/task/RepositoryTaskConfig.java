package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.TextFieldExtractor;

public class RepositoryTaskConfig extends TaskConfig {

	public RepositoryTaskConfig(IdentifierCatalog idRegistry) {
		super(idRegistry);
	}

	public RepositoryTaskConfig(IdentifierCatalog idRegistry, TextFieldExtractor textFieldExtractor) {
		super(idRegistry, textFieldExtractor);
	}

}

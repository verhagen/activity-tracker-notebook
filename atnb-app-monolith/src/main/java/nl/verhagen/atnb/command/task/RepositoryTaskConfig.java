package nl.verhagen.atnb.command.task;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;

public class RepositoryTaskConfig extends TaskConfig {

	public RepositoryTaskConfig(IdentifierCatalog idRegistry) {
		super(idRegistry);
	}

	public RepositoryTaskConfig(IdentifierCatalog idRegistry, TextFieldExtractor textFieldExtractor) {
		super(idRegistry, textFieldExtractor);
	}

}

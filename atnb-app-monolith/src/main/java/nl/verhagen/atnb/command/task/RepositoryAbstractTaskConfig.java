package nl.verhagen.atnb.command.task;

import com.github.verhagen.atnb.core.task.AbstractTaskConfig;
import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;

public class RepositoryAbstractTaskConfig extends AbstractTaskConfig {

	public RepositoryAbstractTaskConfig(IdentifierCatalog idRegistry) {
		super(idRegistry);
	}

	public RepositoryAbstractTaskConfig(IdentifierCatalog idRegistry, TextFieldExtractor textFieldExtractor) {
		super(idRegistry, textFieldExtractor);
	}

}

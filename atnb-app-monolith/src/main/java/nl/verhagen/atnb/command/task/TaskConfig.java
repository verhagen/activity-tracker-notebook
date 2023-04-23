package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.TextFieldExtractor;

public abstract class TaskConfig {
	private final IdentifierCatalog idCatalog;
	private final TextFieldExtractor textFieldExtractor;

	
	public TaskConfig(IdentifierCatalog idCatalog) {
		this(idCatalog, null);
	}
	public TaskConfig(IdentifierCatalog idCatalog, TextFieldExtractor textFieldExtractor) {
		if (idCatalog == null) {
			throw new IllegalArgumentException("Argument 'idRegistry' should not be null.");
		}
		this.idCatalog = idCatalog;
		this.textFieldExtractor = textFieldExtractor;
	}


	public IdentifierCatalog getIdCatalog() {
		return idCatalog;
	}


	public TextFieldExtractor getTextFieldExtractor() {
		return textFieldExtractor;
	}

}

package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.TextFieldExtractor;

public abstract class TaskConfiguration {
	private final IdentifierCatalog idCatalog;
	private final TextFieldExtractor textFieldExtractor;

	
	public TaskConfiguration(IdentifierCatalog idCatalog) {
		this(idCatalog, null);
	}
	public TaskConfiguration(IdentifierCatalog idCatalog, TextFieldExtractor textFieldExtractor) {
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
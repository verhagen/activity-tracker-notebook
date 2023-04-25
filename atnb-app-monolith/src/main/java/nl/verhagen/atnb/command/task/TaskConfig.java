package nl.verhagen.atnb.command.task;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;

public abstract class TaskConfig {
	private final IdentifierCatalog idCatalog;
	private final TextFieldExtractor textFieldExtractor;

	
	public TaskConfig(IdentifierCatalog idCatalog) {
		this(idCatalog, null);
	}
	public TaskConfig(IdentifierCatalog idCatalog, TextFieldExtractor textFieldExtractor) {
		if (idCatalog == null) {
			throw new IllegalArgumentException("Argument 'idCatalogs' should not be null.");
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

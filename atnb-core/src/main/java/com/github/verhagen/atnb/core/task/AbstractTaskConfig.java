package com.github.verhagen.atnb.core.task;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;

public abstract class AbstractTaskConfig {
	private final IdentifierCatalog idCatalog;
	private final TextFieldExtractor textFieldExtractor;

	
	public AbstractTaskConfig(IdentifierCatalog idCatalog) {
		this(idCatalog, null);
	}
	public AbstractTaskConfig(IdentifierCatalog idCatalog, TextFieldExtractor textFieldExtractor) {
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

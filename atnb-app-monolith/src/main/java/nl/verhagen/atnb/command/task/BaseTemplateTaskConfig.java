package nl.verhagen.atnb.command.task;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;

public class BaseTemplateTaskConfig extends TemplateTaskConfig {

	public BaseTemplateTaskConfig(IdentifierCatalog idCatalog) {
		super(idCatalog, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		return null;
	}

}

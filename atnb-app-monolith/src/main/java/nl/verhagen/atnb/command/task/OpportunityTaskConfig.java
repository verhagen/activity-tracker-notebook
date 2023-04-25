package nl.verhagen.atnb.command.task;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.github.verhagen.atnb.core.textfield.DateTextField;
import com.github.verhagen.atnb.core.textfield.StringTextField;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;
import com.github.verhagen.atnb.core.textfield.UriTextField;
import com.github.verhagen.atnb.domain.IdentifierCatalog;
import nl.verhagen.atnb.command.*;

public class OpportunityTaskConfig extends TemplateTaskConfig {


	public OpportunityTaskConfig(IdentifierCatalog idRegistery) {
		super(idRegistery, createTextFieldExtractor());
	}

	
	private static TextFieldExtractor createTextFieldExtractor() {
		return new TextFieldExtractor(Arrays.asList(
				new DateTextField("date", DateTimeFormatter.ofPattern("yyyy.MM.dd"), false)
				, new StringTextField("agent")
				, new StringTextField("organisation")
				, new StringTextField("role")
				, new UriTextField("mail", false)
		));
	}

}

package nl.verhagen.activitylogger.command.task;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import nl.verhagen.activitylogger.command.*;

public class OpportunityTaskConfiguration extends TemplateTaskConfiguration {


	public OpportunityTaskConfiguration(IdentifierCatalog idRegistery) {
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

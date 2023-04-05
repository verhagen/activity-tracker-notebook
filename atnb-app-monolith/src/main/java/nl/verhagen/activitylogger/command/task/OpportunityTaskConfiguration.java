package nl.verhagen.activitylogger.command.task;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import nl.verhagen.activitylogger.command.DateTextField;
import nl.verhagen.activitylogger.command.IdentifierRegistry;
import nl.verhagen.activitylogger.command.StringTextField;
import nl.verhagen.activitylogger.command.TextFieldExtractor;
import nl.verhagen.activitylogger.command.UriTextField;

public class OpportunityTaskConfiguration extends TemplateTaskConfiguration {


	public OpportunityTaskConfiguration(IdentifierRegistry idRegistery) {
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

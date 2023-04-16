package nl.verhagen.activitylogger.command.task;

import java.net.URI;
import java.util.Arrays;

import nl.verhagen.activitylogger.command.IdentifierCatalog;
import nl.verhagen.activitylogger.command.StringTextField;
import nl.verhagen.activitylogger.command.TextFieldExtractor;
import nl.verhagen.activitylogger.command.UriInformationExtractor;
import nl.verhagen.activitylogger.command.UriTextField;

public class IssueTaskConfiguration extends TaskConfiguration {

	public IssueTaskConfiguration(IdentifierCatalog idCatalog) {
		super(idCatalog, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		return new TextFieldExtractor(
				Arrays.asList(
					new StringTextField("title")
					, new UriTextField("uri|url")
				)
				, Arrays.asList(
						new UriInformationExtractor(URI.create("https://jira.organisation.org/browse/"))
				)
		);
	}

}

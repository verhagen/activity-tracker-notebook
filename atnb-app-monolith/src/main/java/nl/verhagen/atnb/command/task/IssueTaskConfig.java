package nl.verhagen.atnb.command.task;

import java.net.URI;
import java.util.Arrays;

import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.StringTextField;
import nl.verhagen.atnb.command.TextFieldExtractor;
import nl.verhagen.atnb.command.UriInformationExtractor;
import nl.verhagen.atnb.command.UriTextField;

public class IssueTaskConfig extends TaskConfig {

	public IssueTaskConfig(IdentifierCatalog idCatalog) {
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

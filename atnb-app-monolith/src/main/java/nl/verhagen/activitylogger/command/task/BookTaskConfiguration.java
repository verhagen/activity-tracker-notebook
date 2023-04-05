package nl.verhagen.activitylogger.command.task;

import java.net.URI;
import java.util.Arrays;

import nl.verhagen.activitylogger.command.IdentifierRegistry;
import nl.verhagen.activitylogger.command.StringTextField;
import nl.verhagen.activitylogger.command.TextFieldExtractor;
import nl.verhagen.activitylogger.command.UriInformationExtractor;
import nl.verhagen.activitylogger.command.UriTextField;

public class BookTaskConfiguration extends RepositoryTaskConfiguration {
	private final URI baseUri;


	public BookTaskConfiguration(IdentifierRegistry idRegistry, URI baseUri) {
		super(idRegistry, createTextFieldExtractor());
		this.baseUri = baseUri;
	}


	public URI getBaseUri() {
		return baseUri;
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		return new TextFieldExtractor(
				Arrays.asList(
					new StringTextField("title")
					, new UriTextField("uri|url")
				)
				, Arrays.asList(
						new UriInformationExtractor(URI.create("https://www.manning.com/books/"))
				)
		);
	}
}

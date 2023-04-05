package nl.verhagen.activitylogger.command.task;

import java.net.URI;
import java.util.Arrays;

import nl.verhagen.activitylogger.command.IdentifierRegistery;
import nl.verhagen.activitylogger.command.StringTextField;
import nl.verhagen.activitylogger.command.TextFieldExtractor;
import nl.verhagen.activitylogger.command.UriInformationExtractor;
import nl.verhagen.activitylogger.command.UriTextField;

public class BookTaskConfiguration extends RepositoryTaskConfiguration {
	private final URI baseUri;


	public BookTaskConfiguration(IdentifierRegistery idRegistery, URI baseUri) {
		super(idRegistery, createTextFieldExtractor());
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

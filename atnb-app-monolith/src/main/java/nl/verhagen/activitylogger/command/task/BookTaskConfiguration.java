package nl.verhagen.activitylogger.command.task;

import java.net.URI;
import java.util.Arrays;

import nl.verhagen.activitylogger.command.*;
import nl.verhagen.activitylogger.command.IdentifierCatalog;

public class BookTaskConfiguration extends RepositoryTaskConfiguration {
	private final URI baseUri;

	public BookTaskConfiguration(IdentifierCatalog idCatalog, URI baseUri) {
		super(idCatalog, createTextFieldExtractor());
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

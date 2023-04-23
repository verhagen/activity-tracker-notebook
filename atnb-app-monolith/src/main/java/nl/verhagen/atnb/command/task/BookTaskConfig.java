package nl.verhagen.atnb.command.task;

import java.net.URI;
import java.util.Arrays;

import nl.verhagen.atnb.command.*;

public class BookTaskConfig extends RepositoryTaskConfig {
	private final URI baseUri;

	public BookTaskConfig(IdentifierCatalog idCatalog, URI baseUri) {
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

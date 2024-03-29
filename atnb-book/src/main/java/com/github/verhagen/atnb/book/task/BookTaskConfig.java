package com.github.verhagen.atnb.book.task;

import java.net.URI;
import java.util.Arrays;

import com.github.verhagen.atnb.core.task.RepositoryAbstractTaskConfig;
import com.github.verhagen.atnb.core.textfield.StringTextField;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;
import com.github.verhagen.atnb.core.textfield.UriInformationExtractor;
import com.github.verhagen.atnb.core.textfield.UriTextField;
import com.github.verhagen.atnb.domain.IdentifierCatalog;

public class BookTaskConfig extends RepositoryAbstractTaskConfig {
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

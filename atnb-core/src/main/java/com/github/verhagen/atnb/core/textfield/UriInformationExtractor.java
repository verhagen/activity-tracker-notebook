package com.github.verhagen.atnb.core.textfield;

import java.net.URI;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UriInformationExtractor implements InformationExtractor<URI> {
	private final URI baseUri;


	public UriInformationExtractor(URI baseUri) {
		this.baseUri = baseUri;
	}

	@Override
	public boolean isInfoExtractionPossible(URI uri) {
		return isMatchingBaseUri(uri);
	}
	public boolean isMatchingBaseUri(URI uri) {
		return uri.toString().startsWith(baseUri.toString());
	}


	@Override
	public String getTitle(URI uri) {
		if (! isMatchingBaseUri(uri)) {
			return "";
		}

		return polish(uri.toString().substring(baseUri.toString().length()));
	}

	@Override
	public String getGroup(URI uri) {
		if (! isMatchingBaseUri(uri)) {
			return "";
		}

		int indexLastDot = uri.getHost().lastIndexOf(".");
		String x = uri.getHost().substring(0, indexLastDot);
		return polish(x.substring(x.lastIndexOf(".") +1));
	}


	private String polish(String text) {
		if (! text.contains("-")) {
			return text.substring(0, 1).toUpperCase() + text.substring(1);
		}
		return Stream.of(text.split("-")).map(t -> t.substring(0, 1).toUpperCase() + t.substring(1)).collect(Collectors.joining(" "));
	}

}

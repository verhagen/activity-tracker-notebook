package com.github.verhagen.atnb.domain;

import java.util.Collection;
import java.util.List;

public interface IdentifierCatalog {
	String BY_DOT = "\\.";

	void add(List<String> identifiers);

	Collection<String> getIdentifiers();

	void accept(Visitor<Node<String, String>> visitor);

	List<String> search(String query);

	boolean isKnownIdentifier(String identifier);

}

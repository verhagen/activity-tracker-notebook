package com.github.verhagen.atnb.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IdentifierCatalogMock implements IdentifierCatalog {
	@Override
	public void add(List<String> identifiers) {

	}

	@Override
	public Collection<String> getIdentifiers() {
		return null;
	}

	@Override
	public void accept(Visitor<Node<String, String>> visitor) {

	}

	@Override
	public List<String> search(String query) {
		return null;
	}

	@Override
	public boolean isKnownIdentifier(String identifier) {
		return false;
	}

//	@Override
//	public void add(String[] identifierPath) {
//	}
//
//	@Override
//	public List<String> search(String[] identifierPath) {
//		return Collections.emptyList();
//	}

}

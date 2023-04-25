package nl.verhagen.atnb.command;

import java.util.Collections;
import java.util.List;

public class IdentifierCatalogMock implements IdentifierCatalogC {

	@Override
	public void add(String[] identifierPath) {
	}

	@Override
	public List<String> search(String[] identifierPath) {
		return Collections.emptyList();
	}

}

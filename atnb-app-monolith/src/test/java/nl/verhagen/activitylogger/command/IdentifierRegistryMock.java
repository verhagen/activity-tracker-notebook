package nl.verhagen.activitylogger.command;

import java.util.Collections;
import java.util.List;

public class IdentifierRegisteryMock implements IdentifierRegistery {

	@Override
	public void add(String[] identifierPath) {
	}

	@Override
	public List<String> search(String[] identifierPath) {
		return Collections.emptyList();
	}

}

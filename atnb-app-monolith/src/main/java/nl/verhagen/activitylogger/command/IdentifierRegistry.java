package nl.verhagen.activitylogger.command;

import java.util.Arrays;
import java.util.List;

public interface IdentifierRegistry {
	static final String BY_DOT = "\\.";

	default List<String> search(String identifier) {
		return search(identifier.split(BY_DOT));
	}

	default void add(String identifier) {
		add(identifier.split(BY_DOT));
	}

	void add(String[] identifierPath);

	List<String> search(String[] identifierPath);

	default String head(String[] array) {
		return array[0];
	}
	default String[] tail(String[] array) {
		return Arrays.copyOfRange(array, 1, array.length - 1);
	}
}

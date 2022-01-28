package nl.verhagen.activitylogger.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings(value = "serial")
public class CommandException extends RuntimeException{

	public CommandException(String msg) {
		super(msg);
	}


	public static CommandException noUniqueIdentifier(String identifier, List<String> foundIdentifiers) {
		return new CommandException("Identifier '" + identifier + "' is not an unique identifier. Found the following identifiers: " + foundIdentifiers);
	}


	public static CommandException noUniqueIdentifier(String[] identifierPath, List<String> foundIdentifiers) {
		return noUniqueIdentifier(Arrays.asList(identifierPath).stream().collect(Collectors.joining(".")), foundIdentifiers);
	}

}

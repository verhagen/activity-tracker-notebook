package nl.verhagen.activitylogger.command.task;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.activitylogger.command.CommandException;
import nl.verhagen.activitylogger.command.CommandName;
import nl.verhagen.activitylogger.command.UriIdentifierConverter;
import nl.verhagen.activitylogger.command.domain.ActivityEvent;
import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.activitylogger.command.domain.ActivityEventImpl;

public class BookTask extends RepositoryTask {
	private Logger logger = LoggerFactory.getLogger(BookTask.class);
	private final BookTaskConfiguration bookTaskCfg;


	public BookTask(ActivityTrackerEventConfiguration activityEventCfg, BookTaskConfiguration bookTaskCfg) {
		super(activityEventCfg, bookTaskCfg, createTaskIdentifier("book", Arrays.asList(CommandName.ADD.name())));
		this.bookTaskCfg = bookTaskCfg;
	}
	

	@Override
	public void execute(String identifier, String command, String text) {
		//CommandName cmdName = getCommand(command);
		Map<String, Object> fields = extractFieldsAsObjects(text);
		UriIdentifierConverter uriIdentifierConverter = new UriIdentifierConverter(bookTaskCfg.getBaseUri(), Arrays.asList("publisher", "book"));
		String[] idPartsFromUri = fields != null && fields.containsKey("uri") ? uriIdentifierConverter.extractIdentification((URI)fields.get("uri")) : null;
		String[] idParts = identifier.split("\\.");
		
		// TODO The TaskIdentifier can also be deeper in the idPath
		// (idPartsFromUri != null) ? idPartsFromUri : idParts
//		if (! identifierPrefix.equalsIgnoreCase(idParts[0])) {
//			throw new IllegalArgumentException("Argument 'identifier' with value '" + identifier + "' should start with the identification '" + identifierPrefix + "'");
//		}
		execute((idPartsFromUri != null) ? idPartsFromUri : idParts, command, fields);
	}


	public void execute(String[] identifierPath, String command, Map<String, Object> fields) {
		if (taskIdentifierColl.isUniqueIdentifierRequired()) {
			int indexOfTaskIdentifier = -1;
			for (int index = 0; index < identifierPath.length; index++) {
				if (taskIdentifierColl.getIdentifier().equals(identifierPath[index])) {
					indexOfTaskIdentifier = index;
				}
			}
			if (indexOfTaskIdentifier > -1) {
				if (isLastElementOfArray(identifierPath, indexOfTaskIdentifier)) {
					throw new TaskHandlerException("Argument 'identifierPath' with value '" + Arrays.asList(identifierPath)
							+ "' does contain the task identifier '" + taskIdentifierColl.getIdentifier() + "', but requires an unique identifier.");
				}
			}
		}
		if (! taskIdentifierColl.isKnownTaskCommand(command)) {
			throw new TaskHandlerException("Argument 'command' with value '" + command + "' is not a known command. Known commands are: " + taskIdentifierColl.getTaskCommands());
		}
		
		List<String> foundIdentifiers = getIdentifierRegistery().search(identifierPath);
		if (foundIdentifiers.size() == 0) {
			getIdentifierRegistery().add(identifierPath);
		}
		else if (foundIdentifiers.size() > 1) {
			throw CommandException.noUniqueIdentifier(identifierPath, foundIdentifiers);
		}
		ActivityEventImpl activityEvent = createActivityEventBuilder().identity(identifierPath).command(command).fields(fields).create();
		switch (command) {
			case "start":
				handle(activityEvent);
				break;
			case "stop":
				handle(activityEvent);
				break;
			case "add":
				init(activityEvent);
				break;
			case "finish":
				handle(activityEvent);
				break;
			default:
				logger.error("No command handling available for '" + command + "' with activity event '" + activityEvent + "'");
				break;
		}
		
	}


	private boolean isLastElementOfArray(String[] identifierPath, int indexOfTaskIdentifier) {
		return indexOfTaskIdentifier == identifierPath.length -1;
	}


	private void init(ActivityEvent activityEvent) {
		logger.info("Execute book init ...  This  should add a book to the books collection...");
	}

	
}

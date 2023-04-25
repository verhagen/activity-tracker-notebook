package nl.verhagen.atnb.command.task;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.domain.ActivityTrackerEvent;
import com.github.verhagen.atnb.core.domain.TaskIdentifier;
import com.github.verhagen.atnb.domain.ActivityTrackerEventImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.atnb.command.CommandException;
import com.github.verhagen.atnb.core.CommandName;
import nl.verhagen.atnb.command.UriIdentifierConverter;
import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;

public class BookTask extends RepositoryTask {
	private Logger logger = LoggerFactory.getLogger(BookTask.class);
	private final BookAbstractTaskConfig bookTaskCfg;


	public BookTask(ActivityTrackerEventConfig atEventCfg, BookAbstractTaskConfig bookTaskCfg) {
		super(atEventCfg, bookTaskCfg, createTaskIdentifier("book", Arrays.asList(CommandName.ADD.name())));
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

	// FIXME [2023.04.05 TV]
	public void execute(String[] identifierPath, String command, Map<String, Object> fields) {
		List<String> foundIdentifiers = bookTaskCfg.getIdCatalog().search(
				Arrays.stream(identifierPath).collect(Collectors.joining(IdentifierCatalog.BY_DOT)));

		TaskIdentifier taskId = null;
		String xx = getTaskIdentifier();

		// FIXME [2023.04.05 TV] See also the disabled tests in BookTaskTest
//		if (getTaskIdentifier().isUniqueIdentifierRequired()) {
//			int indexOfTaskIdentifier = -1;
//			for (int index = 0; index < identifierPath.length; index++) {
//				if (taskIdentifierColl.getIdentifier().equals(identifierPath[index])) {
//					indexOfTaskIdentifier = index;
//				}
//			}
//			if (indexOfTaskIdentifier > -1) {
//				if (isLastElementOfArray(identifierPath, indexOfTaskIdentifier)) {
//					throw new TaskHandlerException("Argument 'identifierPath' with value '" + Arrays.asList(identifierPath)
//							+ "' does contain the task identifier '" + taskIdentifierColl.getIdentifier() + "', but requires an unique identifier.");
//				}
//			}
//		}
//		if (! taskIdentifierColl.isKnownTaskCommand(command)) {
//			throw new TaskHandlerException("Argument 'command' with value '" + command + "' is not a known command. Known commands are: " + taskIdentifierColl.getTaskCommands());
//		}

		if (foundIdentifiers.size() == 0) {
			getIdentifierCatalog().add(Arrays.asList(identifierPath));
		}
		else if (foundIdentifiers.size() > 1) {
			throw CommandException.noUniqueIdentifier(identifierPath, foundIdentifiers);
		}
		ActivityTrackerEventImpl atEvent = createActivityEventBuilder().identity(identifierPath).command(command).fields(fields).create();
		switch (command) {
			case "start":
				handle(atEvent);
				break;
			case "stop":
				handle(atEvent);
				break;
			case "add":
				init(atEvent);
				break;
			case "finish":
				handle(atEvent);
				break;
			default:
				logger.error("No command handling available for '" + command + "' with activity tracker event '" + atEvent + "'");
				break;
		}
		
	}


	private boolean isLastElementOfArray(String[] identifierPath, int indexOfTaskIdentifier) {
		return indexOfTaskIdentifier == identifierPath.length -1;
	}


	private void init(ActivityTrackerEvent activityTrackerEvent) {
		logger.info("Execute book init ...  This  should add a book to the books collection...");
	}

}

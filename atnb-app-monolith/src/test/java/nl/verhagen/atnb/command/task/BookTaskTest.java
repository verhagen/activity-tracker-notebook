package nl.verhagen.atnb.command.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nl.verhagen.atnb.command.AppException;
import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.IdentifierCatalogMock;
import nl.verhagen.atnb.command.domain.ActivityTrackerEvent;
import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.atnb.command.domain.Listener;

public class BookTaskTest {
	private IdentifierCatalog idReg = new IdentifierCatalogMock();
	private ActivityTrackerEventConfiguration activityEventCfg = new ActivityTrackerEventConfiguration("miss-piggy", "london");
	private BookTaskConfiguration bookTaskConfiguration = new BookTaskConfiguration(idReg, URI.create("https://www.manning.com/books/"));

	// TODO [2022.01.14 TV] This should throw an Exception, as there is not enough information, about which specific book this is about.
	// Identifier 'book' is a primary path key and can never be used as a unique identifier. And there is no identification information
	// in the remaining text part.
	@Disabled
	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
			  "book         | start | Argument 'identifierPath' with value '[book]' does contain the task identifier 'book', but requires an unique identifier. "
			, "library.book | start | Argument 'identifierPath' with value '[library, book]' does contain the task identifier 'book', but requires an unique identifier."
			, "library.book.street-coder | jump | Argument 'command' with value 'jump' is not a known command. Known commands are: [help, start, stop, finish, add] "
	})
	public void startWithoutUniqueIdentifier(String identifier, String command, String expException) {
		BookTask task = new BookTask(activityEventCfg, bookTaskConfiguration);
		try {
			task.execute(identifier, command, null);
			fail("Expecting an " + AppException.class.getSimpleName() + " will be thrown.");
		}
		catch (TaskHandlerException te) {
			assertEquals(expException, te.getMessage());
		}
	}

	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
		  "book.graph-databases-in-action | start |  | book.graph-databases-in-action"

		, "book | add    | [Street Coder](https://www.manning.com/books/street-coder) | publisher.manning.book.street-coder"
		, "book | start  | https://www.manning.com/books/street-coder                 | publisher.manning.book.street-coder"
		, "book | start  | Street Coder https://www.manning.com/books/street-coder    | publisher.manning.book.street-coder"
		, "book | stop   | [Street Coder](https://www.manning.com/books/street-coder) | publisher.manning.book.street-coder"
		, "book | finish | [Street Coder](https://www.manning.com/books/street-coder) | publisher.manning.book.street-coder"
		
	})
	public void execute(String identifier, String command, String text, String expectedIdentifier) {
		BookTask task = new BookTask(activityEventCfg, bookTaskConfiguration); 
		task.addListener(new Listener<ActivityTrackerEvent>() {
			@Override
			public void update(ActivityTrackerEvent event) {
				assertEquals(expectedIdentifier, event.getIdentifier());
				assertEquals(command, event.getCommand());
			}
		});
		task.execute(identifier, command, text);
	}
	
}

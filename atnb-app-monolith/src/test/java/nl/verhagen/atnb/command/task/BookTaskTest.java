package nl.verhagen.atnb.command.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;

import com.github.verhagen.atnb.core.task.TaskHandlerException;
import com.github.verhagen.atnb.domain.IdentifierCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.github.verhagen.atnb.core.AppException;
import com.github.verhagen.atnb.domain.ActivityTrackerEvent;
import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;
import com.github.verhagen.atnb.domain.Listener;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookTaskTest {
	@Mock
	private IdentifierCatalog idCatalog;
	private ActivityTrackerEventConfig activityEventCfg = new ActivityTrackerEventConfig("miss-piggy", "london");
	private BookAbstractTaskConfig bookTaskConfig;

	@BeforeEach
	public void setUp() {
		bookTaskConfig = new BookAbstractTaskConfig(idCatalog, URI.create("https://www.manning.com/books/"));
	}

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
		BookTask task = new BookTask(activityEventCfg, bookTaskConfig);
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
		BookTask task = new BookTask(activityEventCfg, bookTaskConfig);
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

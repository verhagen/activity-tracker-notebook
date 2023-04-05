package nl.verhagen.activitylogger.command;

//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
import nl.verhagen.activitylogger.command.domain.ActivityEvent;
import nl.verhagen.activitylogger.command.domain.Listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

public class AppRunnerTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    @Disabled // FIXME Implementation needed (now throws an exception) 
    @Test
    public void startIssue() {
        LocalDateTime timeStamp = LocalDateTime.parse("2021.12.07 13:35", formatter);
        AppRunner appRunner = new AppRunner(new AppRunnerConfiguration());
        appRunner.addListener(new Listener<ActivityEvent>() {
			@Override
			public void update(ActivityEvent event) {
				assertEquals("2021.12.07 14:35", event.getTimeStamp().format(formatter));
				assertEquals("test", event.getIdentifier());
				assertEquals("start", event.getCommand());
			}
		});
        
        
        appRunner.execute(timeStamp, "issue.123", "start");

//        assertEquals("2021.12.07 13:35-  organisation.cs.project.lima.issue.LPBUNI-123  start", result);
    }

    @Disabled // FIXME Implementation needed (now throws an exception) 
    @Test
    public void stopIssue() {
        LocalDateTime timeStamp = LocalDateTime.parse("2021.12.07 13:50", formatter);
        AppRunner appRunner = new AppRunner(new AppRunnerConfiguration());
        appRunner.addListener(new Listener<ActivityEvent>() {
			@Override
			public void update(ActivityEvent event) {
				assertEquals("2021.12.07 14:35", event.getTimeStamp().format(formatter));
				assertEquals("test", event.getIdentifier());
				assertEquals("start", event.getCommand());
			}
		});
        
        appRunner.execute(timeStamp, "issue.123", "stop");

//        assertEquals("2021.12.07 -13:50  organisation.cs.project.lima.issue.LPBUNI-123  stop", result);
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
			  "jump  |      |                          | java.lang.IllegalArgumentException: Argument 'identifier' with value 'jump' does not contain a known task identifier. " 
			, "issue | jump | IllegalArgumentException | java.lang.IllegalArgumentException: Argument 'command' with value 'jump' is not a known command of the IssueTask. Known commands are: [help, start, stop, finish, create]. "
	})
	public void create(String identifier, String command, String a, String b) {
		try {
			new AppRunner(new AppRunnerConfiguration()).execute(identifier, command);
			fail("Expecting an " + AppException.class.getSimpleName() + " will be thrown.");
		}
		catch (AppException ae) {
			assertEquals(b, ae.getMessage());
		}
				
	}

}

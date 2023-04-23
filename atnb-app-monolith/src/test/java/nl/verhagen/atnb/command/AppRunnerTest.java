package nl.verhagen.atnb.command;

import nl.verhagen.atnb.command.domain.ActivityTrackerEvent;
import nl.verhagen.atnb.command.domain.Listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppRunnerTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

	@BeforeEach
	public void setUp() {
		System.setProperty("user.home", "src/test/data/user-home-default");
	}

    @Disabled  // TODO [2023.04.05] Implementation needed (now throws an exception)
    @Test
    public void startIssue() {
        LocalDateTime timeStamp = LocalDateTime.parse("2021.12.07 13:35", formatter);
        AppRunner appRunner = new AppRunner(new AppRunnerConfig());
        appRunner.addListener(new Listener<ActivityTrackerEvent>() {
			@Override
			public void update(ActivityTrackerEvent event) {
				assertEquals("2021.12.07 14:35", event.getTimeStamp().format(formatter));
				assertEquals("test", event.getIdentifier());
				assertEquals("start", event.getCommand());
			}
		});
        
        
        appRunner.execute(timeStamp, "issue.123", "start");

//        assertEquals("2021.12.07 13:35-  organisation.cs.project.lima.issue.LPBUNI-123  start", result);
    }

    @Disabled // TODO [2023.04.05] Implementation needed (now throws an exception)
    @Test
    public void stopIssue() {
        LocalDateTime timeStamp = LocalDateTime.parse("2021.12.07 13:50", formatter);
        AppRunner appRunner = new AppRunner(new AppRunnerConfig());
        appRunner.addListener(new Listener<ActivityTrackerEvent>() {
			@Override
			public void update(ActivityTrackerEvent event) {
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
			new AppRunner(new AppRunnerConfig()).execute(identifier, command);
			fail("Expecting an " + AppException.class.getSimpleName() + " will be thrown.");
		}
		catch (AppException ae) {
			assertEquals(b, ae.getMessage());
		}
	}

	@Disabled
	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
			"issue.OPL-1444 | create | A | B "
	})
	public void createSuccess(String identifier, String command, String a, String b) {
		try {
			new AppRunner(new AppRunnerConfig()).execute(identifier, command);
			fail();
		}
		catch (AppException ae) {
			assertEquals(b, ae.getMessage());
		}
	}

}

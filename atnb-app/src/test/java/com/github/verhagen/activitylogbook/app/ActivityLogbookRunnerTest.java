package com.github.verhagen.activitylogbook.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.github.verhagen.activitylogbook.core.Command;
import com.github.verhagen.activitylogbook.domain.UnknownIdentifierException;

public class ActivityLogbookRunnerTest {
	private ActivityLogbookRunner runner;

	public ActivityLogbookRunnerTest() {
		runner = new ActivityLogbookRunner(Arrays.asList(new String [] { "reading.book.abc" } ));
	}

	@Test
	void unknownIdentifier() {
		try {
			runner.execute(Command.START, "test");
			fail("Expecting an exception");
		}
		catch (UnknownIdentifierException uie) {
			assertEquals("Unknown identifier 'test'", uie.getMessage());
		}
	}

	@Disabled
	@Test
	void startActivity() {
		// TODO Add implementation
		runner.execute(Command.START, "reading.book.abc");
	}

}

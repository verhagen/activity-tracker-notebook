package com.github.verhagen.atnb.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import com.github.verhagen.atnb.core.CommandName;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.github.verhagen.atnb.domain.UnknownIdentifierException;

public class ActivityLogbookRunnerTest {
	private ActivityLogbookRunner runner;

	public ActivityLogbookRunnerTest() {
		runner = new ActivityLogbookRunner(Arrays.asList(new String [] { "reading.book.abc" } ));
	}

	@Test
	void unknownIdentifier() {
		try {
			runner.execute(CommandName.START, "test");
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
		runner.execute(CommandName.START, "reading.book.abc");
	}

}

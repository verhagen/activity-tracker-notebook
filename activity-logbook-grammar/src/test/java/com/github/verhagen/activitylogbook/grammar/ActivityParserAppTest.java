package com.github.verhagen.activitylogbook.grammar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ActivityParserAppTest {

	@ParameterizedTest
	@CsvSource({ "book, (expr book <EOF>)"
			, "reading.book, (expr reading . book <EOF>)"
			, "reading.article, (expr reading . article <EOF>)"
			, "book.roman-architecture-2nd-edition.chapter.republican-rome, (expr book . roman-architecture-2nd-edition . chapter . republican-rome <EOF>)"
	})
	void grammerValidation(String text, String expected) throws Exception {
		assertEquals(expected, new ActivityParserApp().main(text));
	}

}

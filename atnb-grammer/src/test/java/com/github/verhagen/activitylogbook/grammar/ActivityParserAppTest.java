package com.github.verhagen.atnb.grammar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ActivityParserAppTest {

	@ParameterizedTest
	@CsvSource({ 
//			  "book , (expr book <EOF>)"
			  "book , (expr (activityExpression (whatExpression book)) <EOF>)"
//			, "reading.book , (expr reading.book <EOF>)"
			, "reading.book , (expr (activityExpression (whatExpression reading.book)) <EOF>)"
//			, "reading.article, (expr reading.article <EOF>)"
			, "reading.article, (expr (activityExpression (whatExpression reading.article)) <EOF>)"
//			, "book.roman-architecture-2nd-edition.chapter.republican-rome, (expr book . roman-architecture-2nd-edition . chapter . republican-rome <EOF>)"
			, "book.roman-architecture-2nd-edition.chapter.republican-rome, (expr (activityExpression (whatExpression book.roman-architecture-2nd-edition.chapter.republican-rome)) <EOF>)"
			
	})
	void grammerValidation(String text, String expected) throws Exception {
		assertEquals(expected, new ActivityParserApp().main(text));
	}

}

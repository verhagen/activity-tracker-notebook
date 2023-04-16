package com.github.verhagen.activitylogbook.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifierCatalogTest {
	private Logger logger = LoggerFactory.getLogger(IdentifierCatalogTest.class);
	private IdentifierCatalog registery = create();

	@Test
	void createSimpleIdentifiers() throws Exception {
		IdentifierCatalog registery = new IdentifierCatalog();
		List<String> ids = Arrays.asList(new String[] {"java", "kotlin", "type-script"});
		registery.add(ids);

		Collection<String> identifiers = registery.getIdentifiers();
		assertTrue(identifiers.contains("java"));
		assertTrue(identifiers.contains("kotlin"));
		assertTrue(identifiers.contains("type-script"));
	}

	@Test
	void createPathIdentifiers() throws Exception {
		IdentifierCatalog registery = new IdentifierCatalog();
		List<String> ids = Arrays.asList(new String[] {"programming.language.java", "programming.language.kotlin", "programming.language.type-script", "programming.language.java"});
		registery.add(ids);

		Collection<String> identifiers = registery.getIdentifiers();
		assertTrue(identifiers.contains("programming.language.java"));
		assertTrue(identifiers.contains("programming.language.kotlin"));
		assertTrue(identifiers.contains("programming.language.type-script"));

		BreadCrumbsExtractor visitor = new BreadCrumbsExtractor();
		registery.accept(visitor);
		assertEquals("[[programming], [programming, language], [programming, language, type-script], [programming, language, java], [programming, language, kotlin]]", visitor.getBreadCrumbs().toString());
		logger.info(visitor.getBreadCrumbs().toString());
	}

	@ParameterizedTest
	@CsvSource({"programming, [programming.language.type-script; programming.language.java]"
			, "java, [programming.language.java]"
			, "mazes, [book.mazes-for-programmers; project.mazes-for-programmers-type-script; project.mazes-for-programmers]"
			, "prog, [book.mazes-for-programmers; project.mazes-for-programmers-type-script; project.mazes-for-programmers; programming.language.type-script; programming.language.java]"
			, "type, [project.mazes-for-programmers-type-script; programming.language.type-script]"
			, "project.mazes, [project.mazes-for-programmers-type-script; project.mazes-for-programmers]"
			, "p:mazes, [project.mazes-for-programmers-type-script; project.mazes-for-programmers]"
			, "b:mazes, [book.mazes-for-programmers]"
	})
	void createQuery(String query, String expectedResult) throws Exception {
		List<String> results = registery.search(query);
		logger.info("Results for query '" + query + "': " + results.toString());
		for (String expected : expectedResult.substring(1, expectedResult.length() -1).split(";")) {
			assertTrue(results.contains(expected.trim()), "Expected result '" + expected.trim() 
					+ "' not found in item identifiers '" + results + "'.");
		}
	}


	private IdentifierCatalog create() {
		IdentifierCatalog registery = new IdentifierCatalog();
		List<String> ids = Arrays.asList(new String[] {
				  "programming.language.java" 
//				, "programming.language.kotlin" 
				, "programming.language.type-script"
//				, "programming.language.java.library.apache-commons-stringutils"
//				, "programming.language.java.library.junit"
				, "book.mazes-for-programmers"
				, "project.mazes-for-programmers"
				, "project.mazes-for-programmers-type-script"
				});
		registery.add(ids);

		BreadCrumbsExtractor visitor = new BreadCrumbsExtractor();
		registery.accept(visitor);
		assertEquals("[[book], [book, mazes-for-programmers], [project], [project, mazes-for-programmers-type-script], [project, mazes-for-programmers], [programming], [programming, language], [programming, language, type-script], [programming, language, java]]", visitor.getBreadCrumbs().toString());
		logger.info(visitor.getBreadCrumbs().toString());
		return registery;
	}

}

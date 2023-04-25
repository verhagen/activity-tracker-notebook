package com.github.verhagen.atnb.book.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.github.verhagen.atnb.book.domain.Publisher;
import org.hjson.JsonValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PublisherTest {

	@Test
	void fromHjson() throws Exception {
		StringReader reader = asStringReadeer(createProjectJson());
		String jsonString = JsonValue.readHjson(reader).toString();
	}

	@ParameterizedTest
	@CsvSource({ "publisher-manning.hjson, [publisher.manning.book.kubernetes-native-microservices-with-quarkus-and-microprofile; publisher.manning.book.street-coder; publisher.manning.book.graph-databases-in-action; publisher.manning.book.machine-learning-engineering]"
			, "publisher-pragprog.hjson, [publisher.pragprog.book.language-implementation-patterns; publisher.pragprog.book.the-definitive-antlr-4-reference; publisher.pragprog.book.functional-programming-in-java]"
	})
	void create(String fileName, String expectedIdentifiers) {
		Path path = Paths.get("src", "test", "resources").resolve(fileName);
		Publisher publisher = Publisher.fromFile(path);
		
		List<String> identifiers = publisher.getIdentifiers();
		assertNotNull(identifiers);

		String[] expectedArray = expectedIdentifiers.substring(1, expectedIdentifiers.length() -1).split(";");
		assertEquals(expectedArray.length, identifiers.size());
		
		for (String expectedId : expectedIdentifiers.substring(1, expectedIdentifiers.length() -1).split(";")) {
			assertTrue(identifiers.contains(expectedId.trim()), "Expected identifier '" + expectedId.trim() 
					+ "' not found in item identifiers '" + identifiers + "'.");
		}
	}

	private static List<String> createProjectJson() {
		List<String> text = new ArrayList<>();
		text.add("{");
		text.add("    name: manning.com");
		text.add("    tags: [ 'publisher', 'book' ]");
		text.add("    items:");
		text.add("    [");
		text.add("        {");
		text.add("            book-url: https://livebook.manning.com/book/kubernetes-native-microservices-with-quarkus-and-microprofile");
		text.add("        }");
		text.add("        {");
		text.add("            book-url: https://livebook.manning.com/book/street-coder");
		text.add("        }");
		text.add("    ]");
		text.add("}");
		return text;
	}
	
	private static StringReader asStringReadeer(List<String> content) {
		StringBuilder bldr = new StringBuilder();
		for (String line : content) {
			if (bldr.length() > 0) {
				bldr.append("\n");
			}
			bldr.append(line);
		}
		return new StringReader(bldr.toString());

	}

}

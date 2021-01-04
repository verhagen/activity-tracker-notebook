package com.github.verhagen.activitylogbook.book;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.hjson.JsonObject;
import org.hjson.JsonValue;
import org.hjson.Stringify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.verhagen.activitylogbook.domain.Identifier;
import com.github.verhagen.activitylogbook.domain.Organisation;
import com.github.verhagen.activitylogbook.domain.Collection;

public class BookTest {

	@Test
	void fromHjson() throws Exception {
		StringReader reader = asStringReadeer(createProjectJson());
		String jsonString = JsonValue.readHjson(reader).toString();

		System.out.println(jsonString);
		JsonObject jsonObject = JsonValue.readHjson(jsonString).asObject();
		System.out.println(jsonObject.toString(Stringify.FORMATTED));
		
		ObjectMapper objectMapper = new ObjectMapper();
		Collection<Organisation, String, Book> collection = objectMapper.readValue(jsonString, new TypeReference<Collection<Organisation, String, Book>>() {} );
		assertEquals("manning.com", collection.organisation.name);
		assertEquals(2, collection.items.size());
	}

	@ParameterizedTest
	@CsvSource({"publisher-manning.hjson, [kubernetes-native-microservices-with-quarkus-and-microprofile; street-coder; graph-databases-in-action; machine-learning-engineering]"
			, "publisher-pragprog.hjson, [language-implementation-patterns; the-definitive-antlr-4-reference; functional-programming-in-java]"
	})
	void fromFile(String fileName, String expectedIdentifiers) {
		Path path = Paths.get("src", "test", "resources").resolve(fileName);
		if (! path.toFile().exists()) {
			System.err.println("File '" + path.toFile() + "' does not exist.");
		}
		try (FileReader reader = new FileReader(path.toFile())) {
			String jsonString = JsonValue.readHjson(reader).toString();
			ObjectMapper objectMapper = new ObjectMapper();
			Collection<Organisation, String, Book> collection = objectMapper.readValue(jsonString, new TypeReference<Collection<Organisation, String, Book>>() {} );

			for (Book book : collection.items) {
				book.getIdentifier();
			}
//			List<String> identifiers = collection.getIdentifiers();
//			for (String expectedId : expectedIdentifiers.substring(1, expectedIdentifiers.length() -1).split(";")) {
//				assertTrue(identifiers.contains(expectedId.trim()), "Expected identifier '" + expectedId.trim() 
//						+ "' not found in item identifiers '" + identifiers + "'.");
//			}
		}
		catch (JsonMappingException e) {
			fail(e.getMessage());
		}
		catch (JsonProcessingException e) {
			fail(e.getMessage());
		}
		catch (FileNotFoundException e) {
			fail(e.getMessage());
		}
		catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	private static List<String> createProjectJson() {
		List<String> text = new ArrayList<>();
		text.add("{");
		text.add("    organisation:");
		text.add("    {");
		text.add("        name: manning.com");
		text.add("        tags: [ 'publisher', 'book' ]");
		text.add("    }");
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

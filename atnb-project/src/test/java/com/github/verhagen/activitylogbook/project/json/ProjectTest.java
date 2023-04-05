package com.github.verhagen.activitylogbook.project.json;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.hjson.JsonObject;
import org.hjson.JsonValue;
import org.hjson.Stringify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.verhagen.activitylogbook.domain.Collection;
import com.github.verhagen.activitylogbook.domain.Organisation;

public class ProjectTest {

	@Test
	void fromHjson() throws Exception {
		StringReader reader = asStringReadeer(createProjectJson());
		String jsonString = JsonValue.readHjson(reader).toString();

		System.out.println(jsonString);
		JsonObject jsonObject = JsonValue.readHjson(jsonString).asObject();
		System.out.println(jsonObject.toString(Stringify.FORMATTED));

		ObjectMapper objectMapper = new ObjectMapper();
		Company company = objectMapper.readValue(jsonString, Company.class);
		assertEquals("BitBucket", company.name);
		assertEquals("https://bitbucket.com", company.url.toString());
		assertEquals(4, company.items.size());

//		for (Project project : company.items) {
//			System.out.println(project.repositoryUrl);
//			System.out.println(project.tags);
//			System.out.println(project.note);
//			System.out.println("id: " + project.getIdentifier());
//		}
		String prefix = "company.bitbucket.project.";
		List<String> expectedIdentiers = Arrays.asList(new String[] { 
				"world-actor-engine"
				, "codingame-engine"
				, "teads-sponsored-contest"
				, "power-of-thor"
				}); 
		List<String> identifiers = company.getIdentifiers();
		assertEquals(expectedIdentiers.size(), identifiers.size());
		for (String id : expectedIdentiers) {
			String expectedIdentifier = prefix + id;
			assertTrue(identifiers.contains(expectedIdentifier), expecting(expectedIdentifier, identifiers));
		}
//		assertTrue(identifiers.contains(prefix + "world-actor-engine"), expecting(prefix + "world-actor-engine", identifiers));
//		assertTrue(identifiers.contains(prefix + "teads-sponsored-contest"), expecting(prefix + "teads-sponsored-contest", identifiers));
//		assertTrue(identifiers.contains(prefix + "power-of-thor"), expecting(prefix + "power-of-thor", identifiers));
	}
	
	private String expecting(String identifier, List<String> identifiers) {
		return "Expected identifier '" + identifier + "' but was not found in collection " + identifiers + ".";
	}

	@ParameterizedTest
	@CsvSource({"projects-bitbucket.hjson, [company.bitbucket.project.world-actor-engine; company.bitbucket.project.codingame-engine; company.bitbucket.project.teads-sponsored-contest; company.bitbucket.project.power-of-thor]"
			, "projects-github.hjson, [company.github.project.text-adventure; company.github.project.cargo-robot]"
	})
	void fromFile(String fileName, String expectedIdentifiers) {
		Path path = Paths.get("src", "test", "resources").resolve(fileName);
		if (! path.toFile().exists()) {
			System.err.println("File '" + path.toFile() + "' does not exist.");
		}
		try (FileReader reader = new FileReader(path.toFile())) {
			String jsonString = JsonValue.readHjson(reader).toString();
			ObjectMapper objectMapper = new ObjectMapper();
			Company collection = objectMapper.readValue(jsonString, Company.class);

			for (Project project : collection.items) {
				System.out.println(project.repositoryUrl);
				System.out.println(project.tags);
				System.out.println(project.note);
				System.out.println("id: " + project.getIdentifier());
			}

			List<String> identifiers = collection.getIdentifiers();
			for (String expectedId : expectedIdentifiers.substring(1, expectedIdentifiers.length() -1).split(";")) {
				assertTrue(identifiers.contains(expectedId.trim()), "Expected identifier '" + expectedId.trim() 
						+ "' not found in item identifiers '" + identifiers + "'.");
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			fail(e.getMessage());
		}
	}


	private static List<String> createProjectJson() {
		List<String> text = new ArrayList<>();
		text.add("{");
		text.add("    url: https://bitbucket.com");
		text.add("    name: BitBucket");
		text.add("    items: [ ");
		text.add(" 	      {");
		text.add(" 	          repository-url: https://bitbucket.org/verhagen/world-actor-engine");
		text.add("        }");
		text.add("        {");
		text.add("            repository-url: https://bitbucket.org/datecleancoder/codingame-engine");
		text.add("            tags: [ 'java', 'codingame' ]");
		text.add("            note: Codingame - Player and Game Engine");
		text.add("        }");
		text.add("        {");
		text.add("            repository-url: https://bitbucket.org/datecleancoder/teads-sponsored-contest");
		text.add("            tags: [ 'java', 'codingame' ]");
		text.add("        }");
		text.add("        {");
		text.add("            repository-url: https://bitbucket.org/datecleancoder/power-of-thor/");
		text.add("            tags: [");
		text.add("                java   ");
		text.add("                ,    codingame   ");
		text.add("            ]");
		text.add("            note:");
		text.add("            '''");
		text.add("            # The Power of Thor");
		text.add("            ");
		text.add("            This Codingame is a first exersize which gives an idea how it works");
		text.add("            Choose a language in  which you  like to do th exersize, like C, Java, Kotlin, Pyhton, TypeScript, stc, stc.");
		text.add("            ");
		text.add("            '''");
		text.add("        }");
		text.add(" 	  ]");
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

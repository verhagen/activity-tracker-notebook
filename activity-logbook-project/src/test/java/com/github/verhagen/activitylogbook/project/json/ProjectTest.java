package com.github.verhagen.activitylogbook.project.json;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		Organisation<Project> organisation = objectMapper.readValue(jsonString, new TypeReference<Organisation<Project>>() {} );
		assertEquals("bitbucket.com", organisation.name);
		assertEquals(4, organisation.items.size());

		for (Project project : organisation.items) {
			System.out.println(project.repositoryUrl);
			System.out.println(project.tags);
			System.out.println(project.description);
			System.out.println("id: " + project.getIdentifier());
		}
		List<String> identifiers = organisation.getIdentifiers();
		assertEquals(4, identifiers.size());
		assertTrue(identifiers.contains("world-actor-engine"));
		assertTrue(identifiers.contains("codingame-engine"));
		assertTrue(identifiers.contains("teads-sponsored-contest"));
		assertTrue(identifiers.contains("power-of-thor"));
	}
	
	@ParameterizedTest
	@CsvSource({"projects-bitbucket.hjson, [world-actor-engine; codingame-engine; teads-sponsored-contest; power-of-thor]"
			, "projects-github.hjson, [text-adventure; cargo-robot]"
	})
	void fromFile(String fileName, String expectedIdentifiers) {
		Path path = Paths.get("src", "test", "resources").resolve(fileName);
		if (! path.toFile().exists()) {
			System.err.println("File '" + path.toFile() + "' does not exist.");
		}
		try (FileReader reader = new FileReader(path.toFile())) {
			String jsonString = JsonValue.readHjson(reader).toString();
			ObjectMapper objectMapper = new ObjectMapper();
			Organisation<Project> organisation = objectMapper.readValue(jsonString, new TypeReference<Organisation<Project>>() {} );

			for (Project project : organisation.items) {
				System.out.println(project.repositoryUrl);
				System.out.println(project.tags);
				System.out.println(project.description);
				System.out.println("id: " + project.getIdentifier());
			}

			List<String> identifiers = organisation.getIdentifiers();
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
		text.add("    name: bitbucket.com");
		text.add("    items: [ ");
		text.add(" 	      {");
		text.add(" 	          repository-url: https://bitbucket.org/verhagen/world-actor-engine");
//		text.add("       tags: ");
//		text.add("       description: ");
		text.add("        }");
		text.add("        {");
		text.add("            repository-url: https://bitbucket.org/datecleancoder/codingame-engine");
		text.add("            tags: [ 'java', 'codingame' ]");
		text.add("            description: Codingame - Player and Game Engine");
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
		text.add("            description:");
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

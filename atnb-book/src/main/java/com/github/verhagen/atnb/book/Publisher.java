package com.github.verhagen.atnb.book;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.hjson.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.verhagen.atnb.domain.Organisation;

public class Publisher extends Organisation<Book> {
	private static Logger logger = LoggerFactory.getLogger(Publisher.class);

	
	public static Publisher fromFile(Path filePath) {
		Path path = filePath;
//		Path path = Paths.get("src", "test", "resources").resolve(fileName);
		if (! path.toFile().exists()) {
			System.err.println("File '" + path.toFile() + "' does not exist.");
			throw new IllegalArgumentException("File '" + path.toFile() + "' does not exist.");
		}
		else if (! path.toFile().isFile()) {
			System.err.println("File '" + path.toFile() + "' is not a file.");
			throw new IllegalArgumentException("File '" + path.toFile() + "' is not a file.");
		}

		try (FileReader reader = new FileReader(path.toFile())) {
			return fromReader(reader);
		}
		catch (JsonMappingException e) {
			throw new RuntimeException("Unable to do Json mapping '" + filePath + "'", e);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException("Unable to do Json processing '" + filePath + "'", e);
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException("Unable to find file '" + filePath + "'", e);
		}
		catch (IOException e) {
			throw new RuntimeException("Unable to read file '" + filePath + "'", e);
		}

	}


	private static Publisher fromReader(Reader reader) throws IOException {
		String jsonString = JsonValue.readHjson(reader).toString();
		ObjectMapper objectMapper = new ObjectMapper();
		Publisher publisher = objectMapper.readValue(jsonString, Publisher.class);

		return publisher;
	}


	public List<String> getIdentifiers() {
		List<String> identifiers = new ArrayList<>();
		String prefix = String.join(".", getIdentifierType(), getIdentifier()); 
		for (Book book : items) {
			identifiers.add(String.join(".", prefix, book.getIdentifierType(), book.getIdentifier()));
		}
		logger.info("" + identifiers);
		return identifiers;
	}


	@Override
	public String getIdentifierType() {
		return Publisher.class.getSimpleName().toLowerCase();
	}
	
}

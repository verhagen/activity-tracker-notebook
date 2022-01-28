package nl.verhagen.activitylogger.command;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;



public class UriInformationExtractionTest {

	@ParameterizedTest
	@CsvSource({
			"https://www.manning.com/books/street-coder , Street Coder , Manning"
	})
	public void create(String uriStr, String expTitle, 	String expGroup) {
		URI baseUri = URI.create("https://www.manning.com/books/"); 
		UriInformationExtractor infoExtracter = new UriInformationExtractor(baseUri);
		URI uri = URI.create(uriStr);

		// Act && Assert
		assertEquals(expTitle, infoExtracter.getTitle(uri));
		assertEquals(expGroup, infoExtracter.getGroup(uri));
	}

}

package nl.verhagen.activitylogger.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TextFieldExtractorTest {

	@Disabled
	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
		  "      1963.11.22;       John F. Kennedy Shot;      https://en.wikipedia.org/wiki/John_F._Kennedy | 1963.11.22 | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
		, "date: 1963.11.22; title:John F. Kennedy Shot; url: https://en.wikipedia.org/wiki/John_F._Kennedy | 1963.11.22 | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
		, "d:    1963.11.22;     t:John F. Kennedy Shot;   u: https://en.wikipedia.org/wiki/John_F._Kennedy | 1963.11.22 | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
		, "da:   1963.11.22;   tit:John F. Kennedy Shot; uri: https://en.wikipedia.org/wiki/John_F._Kennedy | 1963.11.22 | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
	})
	public void extract(String text, String date, String title, String uri) {
		TextFieldExtractor extractor = new TextFieldExtractor(Arrays.asList(
				new DateTextField("date", DateTimeFormatter.ofPattern("yyyy.MM.dd"))
				, new StringTextField("title")
				, new UriTextField("uri|url")
		));
		
		Map<String, Object> fields = extractor.extractAsObjects(text);
		assertEquals(LocalDate.class, fields.get("date").getClass());
		assertEquals(String.class, fields.get("title").getClass());
		assertEquals(URI.class, fields.get("uri").getClass());

		assertEquals(date, ((LocalDate)fields.get("date")).format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
		assertEquals(title, fields.get("title"));
		assertEquals(uri, fields.get("uri").toString());

		Map<String, String> fieldsAsText = extractor.extract(text);
		assertEquals(date, fieldsAsText.get("date"));
		assertEquals(title, fieldsAsText.get("title"));
		assertEquals(uri, fieldsAsText.get("uri"));
	}

	@Disabled
	@CsvSource(delimiter = '|', value = {
		  "      1963.11.22;       John F. Kennedy Shot;     https://en.wikipedia.org/wiki/John_F._Kennedy | 1963.11.22 | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
		, "date: 1963.11.22; title:John F. Kennedy Shot; url:https://en.wikipedia.org/wiki/John_F._Kennedy | 1963.11.22 | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
		, "d:    1963.11.22;     t:John F. Kennedy Shot;   u:https://en.wikipedia.org/wiki/John_F._Kennedy | 1963.11.22 | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
		, "                ;       John F. Kennedy Shot;                                                   |            | John F. Kennedy Shot | "
		, "                ;   t:  John F. Kennedy Shot;                                                   |            | John F. Kennedy Shot | "
		, "                ;   t:  John F. Kennedy Shot; u:  https://en.wikipedia.org/wiki/John_F._Kennedy |            | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
		, "date: 1963.11.22  title:John F. Kennedy Shot  uri:https://en.wikipedia.org/wiki/John_F._Kennedy | 1963.11.22 | John F. Kennedy Shot | https://en.wikipedia.org/wiki/John_F._Kennedy"
	})
	public void extractWithPossibleEmptyFields(String text, String date, String title, String uri) {
		TextFieldExtractor extractor = new TextFieldExtractor(Arrays.asList(
				new DateTextField("date", DateTimeFormatter.ofPattern("yyyy.MM.dd"), false)  // Not required
				, new StringTextField("title")                                               // Required
				, new UriTextField("uri|url", false)                                         // Not required
		));
		boolean isDateNull = StringUtils.trimToNull(date) == null;
		boolean isUriNull = StringUtils.trimToNull(uri) == null;
		
		Map<String, Object> fields = extractor.extractAsObjects(text);
		// Check types of each field
		if (isDateNull) {
			assertFalse(fields.containsKey("date"));
		}
		else {
			assertEquals(LocalDate.class, fields.get("date").getClass());
		}
		assertEquals(String.class, fields.get("title").getClass());
		if (isUriNull) {
			assertFalse(fields.containsKey("uri"));
		}
		else {
			assertEquals(URI.class, fields.get("uri").getClass());
		}

		// Check content of each field
		if (! isDateNull) {
			assertEquals(date, ((LocalDate)fields.get("date")).format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
		}
		assertEquals(title, fields.get("title"));
		if (! isUriNull) {
			assertEquals(uri, fields.get("uri").toString());
		}

		// Check content as String of each field
		Map<String, String> fieldsAsText = extractor.extract(text);
		if (! isDateNull) {
			assertEquals(date, fieldsAsText.get("date"));
		}
		assertEquals(title, fieldsAsText.get("title"));
		if (! isUriNull) {
			assertEquals(uri, fieldsAsText.get("uri"));
		}
	}


	@ParameterizedTest
    @CsvSource(delimiter = '|', value = {
          "https://www.manning.com/books/street-coder                   | Street Coder   | https://www.manning.com/books/street-coder"
        , "Street Coder A https://www.manning.com/books/street-coder    | Street Coder A | https://www.manning.com/books/street-coder"
        , "[Street Coder B](https://www.manning.com/books/street-coder) | Street Coder B | https://www.manning.com/books/street-coder"
        , "https://www.manning.com/books/street-coder[Street Coder C]   | Street Coder C | https://www.manning.com/books/street-coder"
    })
	public void extractTitleAndUri(String text, String expTitle, String expUri) throws Exception {
		TextFieldExtractor extractor = new TextFieldExtractor(
				Arrays.asList(
						new StringTextField("title")
						, new UriTextField("uri|url")
				)
				, Arrays.asList(
						new UriInformationExtractor(URI.create("https://www.manning.com/books/"))
				)
		);

		// Act
		Map<String, String> fields = extractor.extract(text);

		// Assert
		assertNotNull(fields);
		assertTrue(fields.size() > 0);
		assertEquals(expTitle, fields.get("title"));
		assertEquals(expUri, fields.get("uri"));
//		IdentifierConverter<URI> identifierConverter = new UriIdentifierConverter(URI.create("https://www.manning.com/books/"), Arrays.asList("publisher", "book"));
//		assertEquals(expected, LinkExtractor.extractLink(identifierConverter, text).asMarkDown());
	}


	@Disabled
	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
			  "[MarkDown](https://en.wikipedia.org/wiki/Markdown)         | MarkDown | https://en.wikipedia.org/wiki/Markdown"
			, "https://docs.asciidoctor.org/asciidoctor/latest/[AsciiDoc] | AsciiDoc | https://docs.asciidoctor.org/asciidoctor/latest/"
	})
	public void create(String text, String expTitle, String expUri) {
		TextFieldExtractor extractor = new TextFieldExtractor(Arrays.asList(
				new StringTextField("title")
				, new UriTextField("uri|url")
		));

		// Act
		Map<String, String> fields = extractor.extract(text);

		// Assert
		assertNotNull(fields);
		assertTrue(fields.size() > 0);
		assertEquals(expTitle, fields.get("title"));
		assertEquals(expUri, fields.get("uri"));
	}

}

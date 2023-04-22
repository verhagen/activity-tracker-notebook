package nl.verhagen.atnb.command.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ActivityEntryTest {
	private Logger logger = LoggerFactory.getLogger(ActivityEntryTest.class);
	private static final String BY_DOT = "\\.";
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

	@ParameterizedTest
    @CsvSource(delimiter = '|', value = {
        "2021.12.28 11:28 | book.street-coder | start"
        , "2021.12.20 11:28 | book.street-coder | add"
        , "2021.12.21 13:20 | publisher.manning.book.street-coder | add"
    })
	public void create(String timestampStr, String identifierStr, String commandStr) {
		ActivityTrackerEventConfiguration cfg = new ActivityTrackerEventConfiguration("sam-the-eagle", "london");
		ActivityTrackerEventImpl activityEntry =
				new ActivityTrackerEventImpl.Builder(cfg)
				.timestamp(LocalDateTime.parse(timestampStr, formatter))
				.identity(identifierStr.split(BY_DOT))
				.command(commandStr)
				.create();
		logger.info("act");

		assertEquals(timestampStr, activityEntry.getTimeStamp().format(formatter));
		assertEquals("sam-the-eagle", activityEntry.getAuthor());
		assertEquals(identifierStr, activityEntry.getIdentifier());
		assertEquals(commandStr, activityEntry.getCommand());
		
		activityEntry.accept(new ActivityConvertor());
	}
}

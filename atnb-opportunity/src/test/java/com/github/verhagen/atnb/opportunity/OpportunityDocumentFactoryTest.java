package com.github.verhagen.atnb.opportunity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.verhagen.atnb.io.MediaTypes;

public class OpportunityDocumentFactoryTest {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	private Logger logger = LoggerFactory.getLogger(OpportunityDocumentFactoryTest.class);


	@Test
	void create() {
		// Arrange
		LocalDate date = LocalDate.parse("2020.12.01", formatter);
		Opportunity opp = new Opportunity.Builder()
				.addCreationDate(date)
				.addAgent("New Balance")
				.addOrganisation("Waldisney Ltd")
				.addTitle("Senior Software Engineer")
				.create();
		OpportunityDocumentFactory oppDocFactory = new OpportunityDocumentFactory();
		
		// Act
		String content = oppDocFactory.create(MediaTypes.TEXT_MARKDOWN, opp);

		// Assert
		assertNotNull(content);
		logger.info(content);
		assertTrue(content.contains("# New Balance - Waldisney Ltd - Senior Software Engineer"), "Content '" + content + "' should contain '123'");
		assertTrue(content.contains("- @@@ (New Balance)"), "Content '" + content + "' should contain '- @@@ (New Balance)'");

	}

	@Test
	void createFileName() {
		// Arrange
		LocalDate date = LocalDate.parse("2020.12.01", formatter);
		Opportunity opp = new Opportunity.Builder()
				.addCreationDate(date)
				.addAgent("New Balance")
				.addOrganisation("Waldisney Ltd")
				.addTitle("Senior Software Engineer")
				.create();
		OpportunityDocumentFactory oppDocFactory = new OpportunityDocumentFactory();
		
		// Act
		String fileName = oppDocFactory.createIdentification(opp);
		
		// Assert
		assertNotNull(fileName);
		assertEquals("2020.12.01-new-balance_waldisney-ltd_senior-software-engineer", fileName);
	}
}

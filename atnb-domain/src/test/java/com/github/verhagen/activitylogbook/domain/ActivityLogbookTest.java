package com.github.verhagen.activitylogbook.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ActivityLogbookTest {
	private static final String READING_BOOK = "reading.book.mazes-for-programmers";
	private static final String PROGRAMMING_PROJECT = "programming.project.mazes-for-programmers";
	private Logger logger = LoggerFactory.getLogger(ActivityLogbookTest.class);
	private ActivityLogbook logbook;


	@BeforeEach
	void setUp() {
		logbook = new ActivityLogbook();
		logbook.add(Arrays.asList(new String[] { 
				READING_BOOK 
				, PROGRAMMING_PROJECT 
				} ));

	}

	@Test
	void startAnActivity() {
		logbook.start(READING_BOOK);
		
		List<Activity> activities = logbook.getActivities();
		for (Activity activity : activities) {
			assertEquals(READING_BOOK, activity.getIdentifier());
		}
	}

	@Test
	void startAndStopAnActivity() {
		logbook.start(READING_BOOK);

		List<Activity> activities = logbook.getActivities();
		assertEquals(1, activities.size());
		for (Activity activity : activities) {
			assertEquals(READING_BOOK, activity.getIdentifier());
			assertNotNull(activity.getStartActivity());
			assertNull(activity.getStopActivity());
		}

		logbook.stop(READING_BOOK);
		activities = logbook.getActivities();
		assertEquals(1, activities.size());
		for (Activity activity : activities) {
			assertEquals(READING_BOOK, activity.getIdentifier());
			assertNotNull(activity.getStartActivity());
			assertNotNull(activity.getStopActivity());
		}
	}

	@Test
	void startSameActivityAndstopActivity() {
		logbook.start(READING_BOOK);
		logbook.start(READING_BOOK);
		List<Activity> activities = logbook.getActivities();
		assertEquals(1, activities.size());

		logbook.stop(READING_BOOK);

		activities = logbook.getActivities();
		assertEquals(1, activities.size());

		logbook.stop(READING_BOOK);

		activities = logbook.getActivities();
		assertEquals(1, activities.size());
	}

	@Test
	void startStopStartStopActivity() {
		logbook.start(READING_BOOK);
		List<Activity> activities = logbook.getActivities();
		assertEquals(1, activities.size());

		logbook.stop(READING_BOOK);
		activities = logbook.getActivities();
		assertEquals(1, activities.size());

		logbook.start(READING_BOOK);
		activities = logbook.getActivities();
		assertEquals(2, activities.size());

		logbook.stop(READING_BOOK);
		activities = logbook.getActivities();
		assertEquals(2, activities.size());
	}

	@Test
	void startActivityStopGeneral() {
		logbook.start(READING_BOOK);
		List<Activity> activities = logbook.getActivities();
		assertEquals(1, activities.size());

		logbook.stop();
		activities = logbook.getActivities();
		assertEquals(1, activities.size());
		Activity activity = activities.get(0);
		assertNotNull(activity.getStartActivity());
		assertNotNull(activity.getStopActivity());
		logger.info("" + activity);
	}

	@Test
	void startActivityStartAnotheerActivity() {
		logbook.start(READING_BOOK);
		List<Activity> activities = logbook.getActivities();
		assertEquals(1, activities.size());

		logbook.start(PROGRAMMING_PROJECT);
		activities = logbook.getActivities();
		assertEquals(2, activities.size());
		
		assertEquals(PROGRAMMING_PROJECT,activities.get(0).getIdentifier());
		assertNotNull(activities.get(0).getStartActivity());
		assertNull(activities.get(0).getStopActivity());

		assertEquals(READING_BOOK,activities.get(1).getIdentifier());
		assertNotNull(activities.get(1).getStartActivity());
		assertNotNull(activities.get(1).getStopActivity());
		logger.info("" + toJson(logbook));
//		assertEquals("{'activities':[{'date':'2021.01.14','identifier':'programming.project.mazes-for-programmers','note':null,'startActivity':'2021.01.14 11:05','stopActivity':null,'duration':0,'identifierPath':['programming','project','mazes-for-programmers']},{'date':'2021.01.14','identifier':'reading.book.mazes-for-programmers','note':null,'startActivity':'2021.01.14 11:05','stopActivity':'2021.01.14 11:05','duration':0,'identifierPath':['reading','book','mazes-for-programmers']}]}", toJson(logbook));
	}
	
	

	String toJson(ActivityLogbook logbook) {
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = JsonMapper.builder()
				.findAndAddModules()
				.build();
		try {
			mapper.writeValue(writer, logbook);
		}
		catch (IOException e) {
			logger.error("Unable to create json from logbook.", e);
		}
		return writer.toString().replace('"', '\'');
	}
}

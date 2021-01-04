package com.github.verhagen.activitylogbook.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


/*
 * | date | activity | duration | start | end |
 * | x    |          |          |       |     |
 * |      | x        |          |       |     |
 * | x    | x        |          |       |     |
 * | x    | x        | x        |       |     |
 * |      | x        | x        | x     |     |
 * |      | x        | x        |       | x   |
 * |      | x        | x        | x     | x   |
 * |      | x        |          | x     | x   |
 * | x    | x        | x        | x     | x   |
 *
 */
public class ActivityTest {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	private DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

	@Test
	void noValuesSet() {
		try {
			new Activity.Builder().create();
		}
		catch (RuntimeException re) {
			assertEquals("The required field 'identifier' has no value.\n"
					+ "The required field 'date' has no value.", re.getMessage());
		}
	}

	@Test
	void onlyDateSet() {
		try {
			new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.create();
		}
		catch (RuntimeException re) {
			assertEquals("The required field 'identifier' has no value.", re.getMessage());
		}
	}

	@Test
	void onlyIdentifierSet() {
		try {
			new Activity.Builder()
					.identifier("cooking")
					.create();
		}
		catch (RuntimeException re) {
			assertEquals("The required field 'date' has no value.", re.getMessage());
		}
	}

	@Test
	void bothIdentifierAndDateSet() {
		try {
			Activity activity = new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.identifier("cooking")
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals("cooking", activity.getIdentifier());
			assertEquals(0, activity.getDuration());
			
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

	@Test
	void identifierWithHierargy() {
		try {
			Activity activity = new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.identifier("cooking.goulash")
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals("cooking.goulash", activity.getIdentifier());
			assertEquals(0, activity.getDuration());
		}
		catch (RuntimeException re) {
			fail();
		}
	}

	@Test
	void activityWithDuration() {
		try {
			Activity activity = new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.identifier("cooking.goulash")
					.duration(30)
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals("cooking.goulash", activity.getIdentifier());
			assertEquals(30, activity.getDuration());
		}
		catch (RuntimeException re) {
			fail();
		}
	}

	@Test
	void activityWithNegativeDuration() {
		try {
			new Activity.Builder()
					.duration(-10)
					.create();
		}
		catch (RuntimeException re) {
			assertEquals("Argument 'duration' with value '-10' should be zero or larger. (>= 0)", re.getMessage());
		}
	}

	@Test
	void activityWithStartNoStopNoDate() {
		try {
			Activity activity = new Activity.Builder()
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.01 14:00", formatterDateTime))
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals("backing", activity.getIdentifier());
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

	@Test
	void activityNoStartWithStopNoDate() {
		try {
			Activity activity = new Activity.Builder()
					.identifier("backing")
					.stop(LocalDateTime.parse("2020.12.01 16:30", formatterDateTime))
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals("backing", activity.getIdentifier());
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}


	@Test
	void getDuration() {
		LocalDateTime start = LocalDateTime.parse("2020.12.01 14:00", formatterDateTime);
		LocalDateTime stop = LocalDateTime.parse("2020.12.01 16:30", formatterDateTime);
		assertEquals(150, Activity.getDuration(start, stop));
	}

	@Test
	void activityWithStartWithStopNoDate() {
		try {
			Activity activity = new Activity.Builder()
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.01 14:00", formatterDateTime))
					.stop(LocalDateTime.parse("2020.12.01 16:30", formatterDateTime))
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals(150, activity.getDuration());
			assertEquals("backing", activity.getIdentifier());
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

	@Test
	void activityWithStartWithStopWithDate() {
		try {
			Activity activity = new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.01 14:00", formatterDateTime))
					.stop(LocalDateTime.parse("2020.12.01 16:30", formatterDateTime))
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals(150, activity.getDuration());
			assertEquals("backing", activity.getIdentifier());
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

	@Test
	void activityWithStartWithStopWithOtherDate() {
		try {
			new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.02 14:00", formatterDateTime))
					.stop(LocalDateTime.parse("2020.12.02 16:30", formatterDateTime))
					.create();
		}
		catch (RuntimeException re) {
			assertEquals("The date with value '2020.12.01' is not on the same day as start activity with value '2020.12.02'.\n"
					+ "The date with value '2020.12.01' is not on the same day as stop activity with value '2020.12.02'.", re.getMessage());
		}
	}

	@Test
	void activityStartAfterStop() {
		try {
			new Activity.Builder()
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.02 14:00", formatterDateTime))
					.stop(LocalDateTime.parse("2020.12.02 10:00", formatterDateTime))
					.create();
		}
		catch (RuntimeException re) {
			assertEquals("Start activity with value '2020.12.02 14:00' is not before stop activity with value '2020.12.02 10:00'.", re.getMessage());
		}
	}

	@Test
	void activityStartAfterStopOtherDay() {
		try {
			new Activity.Builder()
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.02 14:00", formatterDateTime))
					.stop(LocalDateTime.parse("2020.12.01 10:00", formatterDateTime))
					.create();
		}
		catch (RuntimeException re) {
			assertEquals("Start activity with value '2020.12.02 14:00' is not before stop activity with value '2020.12.01 10:00'.\n"
					+ "Start activity with value '2020.12.02' is not on the same day as stop activity with value '2020.12.01'.", re.getMessage());
		}
	}

	@Test
	void activityStartStopNotOnSameDay() {
		try {
			new Activity.Builder()
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.02 14:00", formatterDateTime))
					.stop(LocalDateTime.parse("2020.12.03 15:00", formatterDateTime))
					.create();
		}
		catch (RuntimeException re) {
			assertEquals("Start activity with value '2020.12.02' is not on the same day as stop activity with value '2020.12.03'.", re.getMessage());
		}
	}

	@Test
	void activityStartStopSameMoment() {
		try {
			Activity activity = new Activity.Builder()
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.02 14:00", formatterDateTime))
					.stop(LocalDateTime.parse("2020.12.02 14:00", formatterDateTime))
					.create();
			assertEquals("2020.12.02", activity.getDate().format(formatter));
			assertEquals(0, activity.getDuration());
			assertEquals("backing", activity.getIdentifier());
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

	@Test
	void activityStartNoStopWithDuration() {
		try {
			Activity activity = new Activity.Builder()
					.identifier("backing")
					.start(LocalDateTime.parse("2020.12.02 14:00", formatterDateTime))
					.duration(20)
					.create();
			assertEquals("2020.12.02", activity.getDate().format(formatter));
			assertEquals(20, activity.getDuration());
			assertEquals("2020.12.02 14:00", activity.getStartActivity().format(formatterDateTime));
			assertEquals("2020.12.02 14:20", activity.getStopActivity().format(formatterDateTime));
			assertEquals("backing", activity.getIdentifier());
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

	@Test
	void activityNoStartWithStopWithDuration() {
		try {
			Activity activity = new Activity.Builder()
					.identifier("backing")
					.stop(LocalDateTime.parse("2020.12.02 14:00", formatterDateTime))
					.duration(20)
					.create();
			assertEquals("2020.12.02", activity.getDate().format(formatter));
			assertEquals(20, activity.getDuration());
			assertEquals("2020.12.02 13:40", activity.getStartActivity().format(formatterDateTime));
			assertEquals("2020.12.02 14:00", activity.getStopActivity().format(formatterDateTime));
			assertEquals("backing", activity.getIdentifier());
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

	@Test
	void activityDuration24Hours() {
		try {
			Activity activity = new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.identifier("backing")
					.duration(24*60) // 24 hours * 60 minutes
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals(1440, activity.getDuration());
			assertEquals("backing", activity.getIdentifier());
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

	@Test
	void activityDurationLargerThen24Hours() {
		try {
			new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.identifier("backing")
					.duration(24*60 + 1) // 24 hours * 60 minutes
					.create();
		}
		catch (RuntimeException re) {
			assertEquals("Argument 'duration' with value '1441' should be maximal (24 hours * 60 minutes) 1440 minutes. (<= 1440)", re.getMessage());
		}
	}

	@Test
	void activityWithNote() {
		try {
			Activity activity = new Activity.Builder()
					.date(LocalDate.parse("2020.12.01", formatter))
					.identifier("cooking")
					.note("Some additional information.")
					.create();
			assertEquals("2020.12.01", activity.getDate().format(formatter));
			assertEquals("cooking", activity.getIdentifier());
			assertEquals(0, activity.getDuration());
			assertEquals("Some additional information.", activity.getNote());
			
		}
		catch (RuntimeException re) {
			fail(re.getMessage());
		}
	}

}

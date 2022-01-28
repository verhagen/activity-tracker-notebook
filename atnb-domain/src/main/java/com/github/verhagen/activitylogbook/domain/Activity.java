package com.github.verhagen.activitylogbook.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Activity implements Identifier<String> {
	private static final DateTimeFormatter FORMATTER_DATE_WITH_TIME = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
	private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
	private final LocalDate date;
	private final int durationInMinutes; 
	private final String identifier;
	private final String note;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
	private final LocalDateTime startActivity;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
	private final LocalDateTime stopActivity;


	public Activity(Builder bldr) {
		this.date = bldr.getDate();
		this.durationInMinutes = bldr.getDuration();
		this.identifier = bldr.getIdentifier();
		this.note = bldr.getNote();
		this.startActivity = bldr.getStartActivity();
		this.stopActivity = bldr.getStopActivity();
	}

	/** Returns the date on which the activity took place. */
	public LocalDate getDate() {
		return date;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	/** Returns the duration in minutes. */
	public int getDuration() {
		return durationInMinutes;
	}

	public LocalDateTime getStartActivity() {
		return startActivity;
	}

	public LocalDateTime getStopActivity() {
		return stopActivity;
	}

	public String getNote() {
		return note;
	}


	public void accept(Visitor<Activity> visitor) {
		visitor.visit(this);
	}

	@JsonIgnore
	public String[] getIdentifierPath() {
		return identifier.split("\\.");
	}

	@Override
	public String toString() {
		return "Activity{" +
				"date: '" + date +
				"', identifier: '" + identifier +
				"', duration (in minutes): '" + durationInMinutes +
				"', note: '" + note +
				"'}";
	}


	public static boolean isStartBeforeEnd(LocalDateTime start, LocalDateTime stop) {
		return start.equals(stop) || start.isBefore(stop);
	}

	public static boolean isOnSameDay(LocalDateTime start, LocalDateTime end) {
		return isOnSameDay(start.toLocalDate(), end.toLocalDate());
	}
	public static boolean isOnSameDay(LocalDateTime start, LocalDate end) {
		return isOnSameDay(start.toLocalDate(), end);
	}
	public static boolean isOnSameDay(LocalDate start, LocalDateTime end) {
		return isOnSameDay(start, end.toLocalDate());
	}
	public static boolean isOnSameDay(LocalDate start, LocalDate end) {
		return start.getYear() == end.getYear()
				&& start.getMonthValue() == end.getMonthValue()
				&& start.getDayOfMonth() == end.getDayOfMonth();
	}

	public static long getDuration(LocalDateTime start, LocalDateTime stop) {
		return ChronoUnit.MINUTES.between(start, stop);
	}




	public static class Builder {
		private LocalDate date;
		private String identifier;
		private int duration;
		private String note;
		private LocalDateTime startActivity;
		private LocalDateTime stopActivity;


		public Activity create() {
			// When start and end time stamps are given:
			// - validate that start is before end.
			// - validate that start is on same day as end
			// - validate that duration (in minutes) should not exceed 24h x 60min
			// - check in case date is given, as well as start and or stop, that they all are on the same day.
			//   start is leading, before end, before date
			// - check in case start end stop are given, as well as duration, that they are the same.
			//   start with end is leading
			List<String> issues = new ArrayList<>();
			if (startActivity != null && stopActivity != null) {
				if (! isStartBeforeEnd(startActivity, stopActivity)) {
					issues.add("Start activity with value '" + startActivity.format(FORMATTER_DATE_WITH_TIME) 
							+ "' is not before stop activity with value '" + stopActivity.format(FORMATTER_DATE_WITH_TIME) + "'.");
				}
				if (! isOnSameDay(startActivity, stopActivity)) {
					issues.add("Start activity with value '" + startActivity.format(FORMATTER_DATE) 
							+ "' is not on the same day as stop activity with value '" + stopActivity.format(FORMATTER_DATE) + "'.");
				}
			}
			if (date != null && (startActivity != null || stopActivity != null)) {
				if (startActivity != null) {
					if (! isOnSameDay(date, startActivity)) {
						issues.add("The date with value '" + date.format(FORMATTER_DATE) 
						+ "' is not on the same day as start activity with value '" + startActivity.format(FORMATTER_DATE) + "'.");
					}
				}
				if (stopActivity != null) {
					if (! isOnSameDay(date, stopActivity)) {
						issues.add("The date with value '" + date.format(FORMATTER_DATE) 
						+ "' is not on the same day as stop activity with value '" + stopActivity.format(FORMATTER_DATE) + "'.");
					}
				}
			}
			if (duration > 0 && startActivity != null && stopActivity != null) {
				if (duration != Activity.getDuration(startActivity, stopActivity)) {
					issues.add("The given duration with value '" + date.format(FORMATTER_DATE) 
							+ "' is not the same as the duration extracted from the start and stop time stamps this gives the value '" 
							+ Activity.getDuration(startActivity, stopActivity) + "'.");
				}
			}

			// Improve data
			if (startActivity != null && stopActivity != null) {
				this.duration = (int)Activity.getDuration(startActivity, stopActivity);
				this.date = startActivity.toLocalDate();
			}
			else if (startActivity != null || stopActivity != null) {
				if (startActivity == null) {
					if (duration != 0) {
						startActivity = stopActivity.minusMinutes(duration);
					}
					if (date == null) {
						date = stopActivity.toLocalDate();
					}
				}
				else if (stopActivity == null) {
					if (duration != 0) {
						stopActivity = startActivity.plusMinutes(duration);
					}
					if (date == null) {
						date = startActivity.toLocalDate();
					}
				}

			}

			if (identifier == null) {
				issues.add("The required field 'identifier' has no value.");
			}
			if (date == null) {
				issues.add("The required field 'date' has no value.");
			}
			if (issues.size() > 0) {
				throw createIllegalArgumentException(issues);
			}
			return new Activity(this);
		}


		private IllegalArgumentException createIllegalArgumentException(List<String> issues) {
			StringBuilder bldr = new StringBuilder();
			for (String issue : issues) {
				if (bldr.length() > 0) {
					bldr.append("\n");
				}
				bldr.append(issue);
			}
			return new IllegalArgumentException(bldr.toString());
		}


		public Builder date(LocalDate date) {
			this.date = date;
			return this;
		}

		public Builder identifier(String identifier) {
			if (! new IdentifierValidationImpl().isValid(identifier)) {
				throw IdentifierValidationImpl.createIllegalArgumentException("identifier", identifier);
			}
			this.identifier = identifier;
			return this;
		}

		/** Add duration in minutes */
		public Builder duration(int duration) {
			if (duration < 0) {
				throw new IllegalArgumentException("Argument 'duration' with value '" + duration + "' should be zero or larger. (>= 0)");
			}
			else if (duration > 1440) {
				throw new IllegalArgumentException("Argument 'duration' with value '" + duration + "' should be maximal (24 hours * 60 minutes) 1440 minutes. (<= 1440)");
			}
			this.duration = duration;
			return this;
		}

		public Builder start(LocalDateTime startActivity) {
			this.startActivity = startActivity;
			return this;
		}
		public Builder stop(LocalDateTime stopActivity) {
			this.stopActivity = stopActivity;
			return this;
		}

		public Builder note(String note) {
			this.note = note;
			return this;
		}


		public LocalDate getDate() {
			return date;
		}

		public LocalDateTime getStartActivity() {
			return startActivity;
		}

		public LocalDateTime getStopActivity() {
			return stopActivity;
		}


		public int getDuration() {
			return duration;
		}

		public String getIdentifier() {
			return identifier;
		}

		public String getNote() {
			return note;
		}

	}

}

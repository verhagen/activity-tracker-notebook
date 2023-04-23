package nl.verhagen.atnb.command.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ActivityTrackerEventImpl implements VisitorAcceptor<ActivityTrackerEvent>, ActivityTrackerEvent {
	private final static DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
	private final LocalDateTime timeStamp;
	private final String author;
	private final String identifier;
	private final String command;
	private final Map<String, Object> fields;


	public ActivityTrackerEventImpl(Builder bldr) {
		timeStamp = bldr.timstamp != null ? bldr.getTimstamp() : LocalDateTime.now();
		author = bldr.getAuthor() != null ? bldr.getAuthor() : bldr.getActivityEntryConfiguration().getAuthor();
		identifier = bldr.getIdentifier();
		command = bldr.getCommand();
		fields = bldr.getFields();
	}


	@Override
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	@Override
	public String getAuthor() {
		return author;
	}


	@Override
	public String getIdentifier() {
		return identifier;
	}


	@Override
	public String getCommand() {
		return command;
	}


	@Override
	public Map<String, Object> getFields() {
		return fields;
	}


	@Override
	public void accept(Visitor<ActivityTrackerEvent> visitor) {
		visitor.visit(this);
	}


	@Override
	public String toString() {
		return "author: '" + author 
				+ "'  timestamp: '" + timeStamp.format(formatter) 
				+ "'  identifier: '" + identifier 
				+ "'  command: '" + command
				+ (fields != null ? "'  fields: '" + fields : "")
				+ "'";
	}




	public static class Builder {
		private ActivityTrackerEventConfig cfg;
		private LocalDateTime timstamp;
		private String author;
		private String identifier;
		private String command;
		private Map<String, Object> fields;

		public Builder(ActivityTrackerEventConfig cfg) {
			this.cfg = cfg;
		}

		public ActivityTrackerEventImpl create() {
			return new ActivityTrackerEventImpl(this);
		}

		
		public ActivityTrackerEventConfig getActivityEntryConfiguration() {
			return cfg;
		}

		public Builder identity(String[] identifierPath) {
			identifier = Arrays.asList(identifierPath).stream().collect(Collectors.joining("."));
			return this;
		}

		public Builder command(String command) {
			this.command = command;
			return this;
		}

		public Builder timestamp(LocalDateTime timstamp) {
			this.timstamp = timstamp;
			return this;
		}
		
		public Builder author(String author) {
			this.author = author;
			return this;
		}

		public Builder fields(Map<String, Object> fields) {
			this.fields = fields;
			return this;
		}

		public String getIdentifier() {
			return identifier;
		}

		public String getCommand() {
			return command;
		}

		public LocalDateTime getTimstamp() {
			return timstamp;
		}

		public String getAuthor() {
			return author;
		}

		public Map<String, Object> getFields() {
			return fields;
		}

	}

}

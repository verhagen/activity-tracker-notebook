package com.github.verhagen.atnb.domain;

import java.time.LocalDateTime;
import java.util.Map;

public interface ActivityTrackerEvent {

	LocalDateTime getTimeStamp();

	String getAuthor();

	String getIdentifier();

	String getCommand();

	Map<String, Object> getFields();

	void accept(Visitor<ActivityTrackerEvent> visitor);

}
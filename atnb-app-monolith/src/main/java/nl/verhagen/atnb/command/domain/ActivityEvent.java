package nl.verhagen.atnb.command.domain;

import java.time.LocalDateTime;
import java.util.Map;

public interface ActivityEvent {

	LocalDateTime getTimeStamp();

	String getAuthor();

	String getIdentifier();

	String getCommand();

	Map<String, Object> getFields();

	void accept(Visitor<ActivityEvent> visitor);

}
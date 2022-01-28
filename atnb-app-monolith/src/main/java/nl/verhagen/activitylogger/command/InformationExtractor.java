package nl.verhagen.activitylogger.command;

import java.net.URI;

public interface InformationExtractor<T> {


	boolean isInfoExtractionPossible(URI uri);

	String getTitle(T t);

	String getGroup(T t);

}

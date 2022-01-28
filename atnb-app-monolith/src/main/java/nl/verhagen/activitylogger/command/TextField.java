package nl.verhagen.activitylogger.command;

public interface TextField {

	String getKey();

	boolean isKeyMatch(String string);

	Object parse(String text);

	String format(Object value);

}

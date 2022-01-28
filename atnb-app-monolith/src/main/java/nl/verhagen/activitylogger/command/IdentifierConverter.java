package nl.verhagen.activitylogger.command;

public interface IdentifierConverter<T> {

	boolean isIdentification(T t);

	String[] extractIdentification(T t);

}

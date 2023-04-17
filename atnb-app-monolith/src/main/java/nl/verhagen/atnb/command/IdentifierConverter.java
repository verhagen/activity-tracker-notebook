package nl.verhagen.atnb.command;

public interface IdentifierConverter<T> {

	boolean isIdentification(T t);

	String[] extractIdentification(T t);

}

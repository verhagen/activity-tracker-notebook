package nl.verhagen.atnb.command.domain;

public interface Visitor<T> {

	void visit(T type);

}

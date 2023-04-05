package nl.verhagen.activitylogger.command.domain;

public interface Visitor<T> {

	void visit(T type);

}

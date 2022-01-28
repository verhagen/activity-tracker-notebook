package nl.verhagen.activitylogger.command.domain;

public interface VisitorAcceptor<T> {
	
	void accept(Visitor<T> visitor);

}

package nl.verhagen.activitylogger.command.domain;

public interface Listener<T> {

	void update(T type);

}

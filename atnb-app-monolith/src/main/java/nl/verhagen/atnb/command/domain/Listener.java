package nl.verhagen.atnb.command.domain;

public interface Listener<T> {

	void update(T type);

}

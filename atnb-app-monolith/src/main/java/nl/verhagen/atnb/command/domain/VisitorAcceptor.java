package nl.verhagen.atnb.command.domain;

public interface VisitorAcceptor<T> {
	
	void accept(Visitor<T> visitor);

}

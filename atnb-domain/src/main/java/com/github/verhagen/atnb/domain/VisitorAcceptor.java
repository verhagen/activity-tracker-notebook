package com.github.verhagen.atnb.domain;

public interface VisitorAcceptor<T> {
	
	void accept(Visitor<T> visitor);

}

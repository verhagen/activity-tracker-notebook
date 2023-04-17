package com.github.verhagen.atnb.domain;

public interface Visitor<T> {

	void visit(T type);

}

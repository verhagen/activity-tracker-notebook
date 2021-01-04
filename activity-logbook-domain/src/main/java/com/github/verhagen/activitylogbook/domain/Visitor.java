package com.github.verhagen.activitylogbook.domain;

public interface Visitor<T> {

	void visit(T type);

}

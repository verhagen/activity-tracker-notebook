package com.github.verhagen.activitylogbook.core;

public interface Visitor<T> {

	void visit(T type);

}

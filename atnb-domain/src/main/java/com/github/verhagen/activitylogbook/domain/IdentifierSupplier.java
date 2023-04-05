package com.github.verhagen.activitylogbook.domain;

public interface IdentifierSupplier<T> extends Identifier<T> {

	String getIdentifierType();

}

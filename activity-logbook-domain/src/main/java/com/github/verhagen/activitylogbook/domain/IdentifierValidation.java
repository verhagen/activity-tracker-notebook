package com.github.verhagen.activitylogbook.domain;

public interface IdentifierValidation<T> {

	boolean isValid(T identifier);

}

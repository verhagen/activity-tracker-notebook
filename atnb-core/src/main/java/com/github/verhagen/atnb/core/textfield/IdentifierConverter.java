package com.github.verhagen.atnb.core.textfield;

public interface IdentifierConverter<T> {

	boolean isIdentification(T t);

	String[] extractIdentification(T t);

}

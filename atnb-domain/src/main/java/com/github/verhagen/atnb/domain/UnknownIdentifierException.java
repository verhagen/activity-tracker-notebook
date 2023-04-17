package com.github.verhagen.atnb.domain;

public class UnknownIdentifierException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnknownIdentifierException(String identifier) {
		super("Unknown identifier '" + identifier + "'");
	}

}

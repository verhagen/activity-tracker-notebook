package com.github.verhagen.atnb.core.textfield;

import com.github.verhagen.atnb.core.textfield.TextField;

import java.util.stream.Stream;

public abstract class AbstractTextField implements TextField {
	private final String[] key;  // Primary key first, then other options. Abbreviations are made automatically. Separate keys by vertical bar '|'.
	                             // The primary key is also used in the value map as key.
	private final boolean isRequired;
	private final Class<?> clazz;


	public AbstractTextField(Class<?> clazz, String key, boolean isRequired) {
		this.clazz = clazz;
		this.key = key.contains("|") ? key.split("\\|") : new String[] { key } ;
		this.isRequired = isRequired;
	}


	public String[] getKeys() {
		return key;
	}

	public String getKey() {
		return key[0];
	}


	public boolean isRequired() {
		return isRequired;
	}


	public Class<?> getClazz() {
		return clazz;
	}


	@Override
	public boolean isKeyMatch(String keyToCheck) {
		return Stream.of(key).anyMatch(k -> k.startsWith(keyToCheck));
	}


	@Override
	public abstract Object parse(String text);

}

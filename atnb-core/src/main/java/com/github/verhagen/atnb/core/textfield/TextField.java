package com.github.verhagen.atnb.core.textfield;

public interface TextField {

	String getKey();

	boolean isKeyMatch(String string);

	Object parse(String text);

	String format(Object value);

}

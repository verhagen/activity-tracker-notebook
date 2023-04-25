package com.github.verhagen.atnb.core.textfield;

import java.net.URI;

public interface InformationExtractor<T> {


	boolean isInfoExtractionPossible(URI uri);

	String getTitle(T t);

	String getGroup(T t);

}

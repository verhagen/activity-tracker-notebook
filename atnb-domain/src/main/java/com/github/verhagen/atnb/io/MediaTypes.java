package com.github.verhagen.atnb.io;

/**
 * https://www.iana.org/assignments/media-types
 */
public class MediaTypes {

	public static final MediaType APPLICATION_JSON = new  MediaType.Builder().addType("application").addSubtype("json").create();
	public static final MediaType APPLICATION_XHTML_XML = new  MediaType.Builder().addType("application").addSubtype("xhtml+xml").create();

//	public static final MediaType TEXT_CALENDAR    = new  MediaType.Builder().addType("text").addSubtype("calendar").create();
	public static final MediaType TEXT_CSV         = new  MediaType.Builder().addType("text").addSubtype("csv").create();
	public static final MediaType TEXT_CSV_SCHEMA  = new  MediaType.Builder().addType("text").addSubtype("csv-schema").create();
	public static final MediaType TEXT_HTML        = new  MediaType.Builder().addType("text").addSubtype("html").create();
	public static final MediaType TEXT_MARKDOWN    = new  MediaType.Builder().addType("text").addSubtype("markdown").create();
	public static final MediaType TEXT_ASCIIDOC    = new  MediaType.Builder().addType("text").addSubtype("asciidoc").create();;

	//	application/xhtml+xml
}

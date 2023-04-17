package com.github.verhagen.atnb.opportunity;

import com.github.verhagen.atnb.io.MediaType;

public class DocumentFactoryException extends RuntimeException {
	private final MediaType mediaType;
	private final Class<?> clazz;


	public DocumentFactoryException(MediaType mediaType, Class<?> clazz) {
		super("The MediaType '" + mediaType + "' is not supported by the class '" + clazz + "'.");
		this.mediaType = mediaType;
		this.clazz = clazz;
	}
	

	public MediaType getMediaType() {
		return mediaType;
	}

	public Class<?> getClazz() {
		return clazz;
	}


	public static DocumentFactoryException createMediaTypeNotSupported(MediaType mediaType, Class<?> clazz) {
		return new DocumentFactoryException(mediaType, clazz);
	}

}

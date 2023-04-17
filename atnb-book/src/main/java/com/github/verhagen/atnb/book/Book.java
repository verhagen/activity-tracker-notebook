package com.github.verhagen.atnb.book;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.verhagen.atnb.domain.IdentifierSupplier;

public class Book implements IdentifierSupplier<String> {
	@JsonProperty("book-url")
	public URI bookUrl;
	public String title;


	@Override
	public String getIdentifier() {
		if (bookUrl != null) {
			String path = bookUrl.getPath();
			if (bookUrl.getPath().endsWith("/")) {
				path = bookUrl.getPath().substring(0, path.lastIndexOf("/"));
			}
			
			if (path.lastIndexOf("/") > -1) {
				return path.substring(path.lastIndexOf("/") +1);
			}
			return path;
		}

		if (title != null) {
			return title.toLowerCase().replace(" ", "-");
		}

		return "identifier-unkown";
	}


	@Override
	public String getIdentifierType() {
		return Book.class.getSimpleName().toLowerCase();
	}


//	public static class Builder {
//		private String identifier;
//
//		public Book create() {
//			return new Book(this);
//		}
//
//		public Builder add(String identifier) {
//			if (! new ActivityIdentifierValidation().isValidIdentifier(identifier)) {
//				ActivityIdentifierValidation.createIllegalArgumentException("identifier", identifier);
//			}
//			this.identifier = identifier;
//			return this;
//		}
//
//		public String getIdentifier() {
//			return identifier;
//		}
//	}

}

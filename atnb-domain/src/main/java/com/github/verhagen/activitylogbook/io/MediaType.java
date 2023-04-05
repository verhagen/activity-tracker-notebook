package com.github.verhagen.activitylogbook.io;

public class MediaType {
	private final String type;
	private final String subtype;

	public MediaType(Builder bldr) {
		type = bldr.getType();
		subtype = bldr.getSubtype();
	}

	public String getType() {
		return type;
	}

	public String getSubtype() {
		return subtype;
	}
	
	public String get() {
		return type + '/' + subtype;
	}

	public static class Builder {
		private String type;
		private String subtype;
		
		public MediaType  create() {
			return new MediaType(this);
		}


		public String getType() {
			return type;
		}

		public Builder addType(String type) {
			this.type = type;
			return this;
		}

		public String getSubtype() {
			return subtype;
		}

		public Builder addSubtype(String subtype) {
			this.subtype = subtype;
			return this;
		}
	}

}

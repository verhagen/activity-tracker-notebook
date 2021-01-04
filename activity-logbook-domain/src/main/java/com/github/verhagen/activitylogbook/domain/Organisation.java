package com.github.verhagen.activitylogbook.domain;

import java.net.URI;
import java.util.List;

public class Organisation {
	public URI url;
	public String name;
	public List<String> tags;
	private Class<?> clazz;

	public Organisation() {
		
	}

	public void setType(Class<?> clazz) {
		this.clazz = clazz;
	}
	public Class<?> getType() {
		return clazz;
	}

}

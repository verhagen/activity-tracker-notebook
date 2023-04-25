package com.github.verhagen.atnb.domain;

public class ActivityTrackerEventConfig {
	/** The Person or System that uses the <i>Activity Tracker and Notebook App<i>, and triggers the creation of events. */
	private final String author;
	private final String location;


	public ActivityTrackerEventConfig(String author, String location) {
		this.author = author;
		this.location = location;
	}


	public String getAuthor() {
		return author;
	}

	public String getLocation() {
		return location;
	}

}

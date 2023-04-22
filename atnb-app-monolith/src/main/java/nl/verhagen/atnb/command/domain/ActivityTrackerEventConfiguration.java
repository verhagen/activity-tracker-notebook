package nl.verhagen.atnb.command.domain;

public class ActivityTrackerEventConfiguration {
	/** The Person or System that uses the <i>Activity Tracker and Notebook App<i>, and triggers the creation of events. */
	private final String author;
	private final String location;


	public ActivityTrackerEventConfiguration(String author, String location) {
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

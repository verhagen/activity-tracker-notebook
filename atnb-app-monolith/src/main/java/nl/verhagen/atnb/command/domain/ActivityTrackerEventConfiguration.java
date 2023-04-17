package nl.verhagen.atnb.command.domain;

public class ActivityTrackerEventConfiguration {
	/** The Person or System that uses the <i>Activity Tracker - Notebook App<i>, and triggers the creation of events. */
	private final String author;


	public ActivityTrackerEventConfiguration(String author) {
		this.author = author;
	}


	public String getAuthor() {
		return author;
	}

}

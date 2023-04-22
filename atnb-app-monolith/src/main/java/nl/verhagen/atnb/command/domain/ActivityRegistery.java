package nl.verhagen.atnb.command.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityRegistery implements VisitorAcceptor<ActivityTrackerEvent> {
	private List<ActivityTrackerEventImpl> activities = new ArrayList<>();

	public void add(ActivityTrackerEventImpl activityEntry) {
		activities.add(activityEntry);
	}

	public List<ActivityTrackerEventImpl> search(String query) {
//		if (query.contains("author")) {
//			
//		}
		return activities.stream().filter(a -> a.getAuthor().equals(query)).collect(Collectors.toList());
	}

	@Override
	public void accept(Visitor<ActivityTrackerEvent> visitor) {
		activities.stream().forEach(ae -> ae.accept(visitor));
	}

}

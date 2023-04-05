package nl.verhagen.activitylogger.command.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityRegistery implements VisitorAcceptor<ActivityEvent> {
	private List<ActivityEventImpl> activities = new ArrayList<>();

	public void add(ActivityEventImpl activityEntry) {
		activities.add(activityEntry);
	}

	public List<ActivityEventImpl> search(String query) {
//		if (query.contains("author")) {
//			
//		}
		return activities.stream().filter(a -> a.getAuthor().equals(query)).collect(Collectors.toList());
	}

	@Override
	public void accept(Visitor<ActivityEvent> visitor) {
		activities.stream().forEach(ae -> ae.accept(visitor));
	}

}

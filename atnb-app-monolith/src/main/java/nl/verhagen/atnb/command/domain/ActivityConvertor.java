package nl.verhagen.atnb.command.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.atnb.command.CommandName;

public class ActivityConvertor implements Visitor<ActivityTrackerEvent> {
	private Logger logger = LoggerFactory.getLogger(ActivityConvertor.class);
	private ActivityTrackerEvent currentActivityEntry;
//	private Handler<Activity> handler;
	
	@Override
	public void visit(ActivityTrackerEvent activityEntry) {
		if (CommandName.START.name() == activityEntry.getCommand()
				&& currentActivityEntry == null) {
			currentActivityEntry = activityEntry;
		}
		else if (CommandName.START.name() == activityEntry.getCommand()
				&& currentActivityEntry != null
				&& (! activityEntry.getIdentifier().equals(currentActivityEntry.getIdentifier()))) {
			logger.info("Create new Activity\n    " + currentActivityEntry + "\n    " + activityEntry);
//			handler.handle(new Activity.Builder().author().start().end().identification().create());
			currentActivityEntry = activityEntry;
		}
		else if (CommandName.STOP.name() == activityEntry.getCommand() 
				&& activityEntry.getIdentifier()  == currentActivityEntry.getIdentifier()) {
			logger.info("Create new Activity\n    " + currentActivityEntry + "\n    " + activityEntry);
			currentActivityEntry = null;
		}
		//logger.info("visiting " + activityEntry.getTimeStamp());
	}

}

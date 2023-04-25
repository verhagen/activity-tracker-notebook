package com.github.verhagen.atnb.core.domain;

import com.github.verhagen.atnb.core.CommandName;
import com.github.verhagen.atnb.domain.ActivityTrackerEvent;
import com.github.verhagen.atnb.domain.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ActivityConvertor implements Visitor<ActivityTrackerEvent> {
	private Logger logger = LoggerFactory.getLogger(ActivityConvertor.class);
	private ActivityTrackerEvent currentAtEvent;
//	private Handler<Activity> handler;
	
	@Override
	public void visit(ActivityTrackerEvent atEvent) {
		if (CommandName.START.name() == atEvent.getCommand()
				&& currentAtEvent == null) {
			currentAtEvent = atEvent;
		}
		else if (CommandName.START.name() == atEvent.getCommand()
				&& currentAtEvent != null
				&& (! atEvent.getIdentifier().equals(currentAtEvent.getIdentifier()))) {
			logger.info("Create new Activity\n    " + currentAtEvent + "\n    " + atEvent);
//			handler.handle(new Activity.Builder().author().start().end().identification().create());
			currentAtEvent = atEvent;
		}
		else if (CommandName.STOP.name() == atEvent.getCommand()
				&& atEvent.getIdentifier()  == currentAtEvent.getIdentifier()) {
			logger.info("Create new Activity\n    " + currentAtEvent + "\n    " + atEvent);
			currentAtEvent = null;
		}
		//logger.info("visiting " + activityEntry.getTimeStamp());
	}

}

package com.github.verhagen.activitylogbook.core;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.verhagen.activitylogbook.domain.Activity;
import com.github.verhagen.activitylogbook.domain.ActivityLogbook;
import com.github.verhagen.activitylogbook.domain.IdentifierCatalog;
import com.github.verhagen.activitylogbook.domain.UnknownIdentifierException;

public class Engine {
	private Logger logger = LoggerFactory.getLogger(Engine.class);
	private IdentifierCatalog registery = new IdentifierCatalog();
	private ActivityLogbook activityLogbook = new ActivityLogbook();


	public Engine() {
//		Path dataPath = Paths.get("src", "main", "resources");
	}

	public void add(List<String> identifiers) {
		registery.add(identifiers);
	}


	public void execute(Command command, String identifier, String note) {
		if (! registery.isKnownIdentifier(identifier)) {
			logger.error("Unkown identifier '" + identifier + "'");
			throw new UnknownIdentifierException(identifier);
		}

		Activity activity = null;
		switch (command) {
		case START:
			activity = new Activity.Builder()
			.start(LocalDateTime.now())
			.identifier(identifier)
			.note(note)
			.create();
			break;
		case STOP:
		default:
			break;
		}
//		activityLogbook.add(activity);
	}

	public void execute(Command command, String identifier) {
		execute(command, identifier, null);
	}

}

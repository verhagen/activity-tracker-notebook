package com.github.verhagen.activitylogbook.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ActivityLogbook {
	@JsonIgnore
	private Logger logger = LoggerFactory.getLogger(ActivityLogbook.class);
	private IdentifierRegistery registery = new IdentifierRegistery();
	private List<Activity> logbook = new ArrayList<>();


	public void add(List<String> identifiers) {
		registery.add(identifiers);
	}


	public void start(String identifier) {
		if (! registery.isKnownIdentifier(identifier)) {
			throw new UnknownIdentifierException(identifier);
		}

		Optional<Activity> findFirst = logbook.stream().filter(a -> a.getIdentifier() == identifier).findFirst();
		if (findFirst.isPresent()) {
			Activity foundActivity = findFirst.get();
			if (foundActivity.getStartActivity() != null && foundActivity.getStopActivity() == null) {
				logger.info("Activity with identifier '" + identifier + "' already started.");
				return;
			}
		}
		stop();
		Activity activity = new Activity.Builder()
				.identifier(identifier)
				.start(LocalDateTime.now())
				.create();
		logbook.add(0, activity);
	}


	public List<Activity> getActivities() {
		return Collections.unmodifiableList(logbook);
	}


	public void stop(String identifier) {
		if (! registery.isKnownIdentifier(identifier)) {
			throw new UnknownIdentifierException(identifier);
		}

		Optional<Activity> findFirst = logbook.stream().filter(a -> a.getIdentifier() == identifier)
				.findFirst();
		if (findFirst.isPresent()) {
			Activity foundActivity = findFirst.get();
			Activity stoppedActivity = new Activity.Builder()
					.identifier(identifier)
					.start(foundActivity.getStartActivity())
					.stop(LocalDateTime.now())
					.create();
			logger.info("Is removed? " + logbook.remove(foundActivity));
			logbook.add(stoppedActivity);
		}
	}


	public void stop() {
		Optional<Activity> findFirst = logbook.stream().filter(a -> a.getStopActivity() == null).findFirst();
		if (findFirst.isPresent()) {
			Activity foundActivity = findFirst.get();
			stop(foundActivity.getIdentifier());
		}
	}


	public boolean isKnownIdentifier(String identifier) {
		return registery.isKnownIdentifier(identifier);
	}


	public Collection<String> getIdentifiers() {
		return registery.getIdentifiers();
	}

	
}

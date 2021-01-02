package com.github.verhagen.activitylogbook.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.verhagen.activitylogbook.domain.ActivityIdentifierValidation;

public class Repository<T> {
	private Logger logger = LoggerFactory.getLogger(Repository.class);
	private Map<String, T> typeByIdentifier = new HashMap<>();


	public T get(String identifier) {
		if (! typeByIdentifier.containsKey(identifier)) {
			if (! new ActivityIdentifierValidation().isValid(identifier)) {
				logger.warn(ActivityIdentifierValidation.createMessage("identifier", identifier));
			}
			logger.debug("No instance found for identifier '" + identifier + "'");
		}
		return typeByIdentifier.get(identifier);
	}

}

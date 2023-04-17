package com.github.verhagen.atnb.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.verhagen.atnb.domain.IdentifierValidationImpl;

public class Repository<T> {
	private Logger logger = LoggerFactory.getLogger(Repository.class);
	private Map<String, T> typeByIdentifier = new HashMap<>();


	public T get(String identifier) {
		if (! typeByIdentifier.containsKey(identifier)) {
			if (! new IdentifierValidationImpl().isValid(identifier)) {
				logger.warn(IdentifierValidationImpl.createMessage("identifier", identifier));
			}
			logger.debug("No instance found for identifier '" + identifier + "'");
		}
		return typeByIdentifier.get(identifier);
	}

}

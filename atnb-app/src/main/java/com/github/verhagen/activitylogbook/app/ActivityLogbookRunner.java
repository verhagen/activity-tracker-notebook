package com.github.verhagen.activitylogbook.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.github.verhagen.activitylogbook.book.Publisher;
import com.github.verhagen.activitylogbook.core.Command;
import com.github.verhagen.activitylogbook.core.Engine;
import com.github.verhagen.activitylogbook.domain.ActivityLogbook;
import com.github.verhagen.activitylogbook.domain.UnknownIdentifierException;

public class ActivityLogbookRunner {
	private ActivityLogbook logbook = new ActivityLogbook();

	public ActivityLogbookRunner(List<String> identifiers) {
		logbook.add(identifiers);

		Path srcPath = Paths.get("src", "main", "resources");
		Publisher publisher = Publisher.fromFile(srcPath.resolve("publisher-manning.hjson"));
		logbook.add(publisher.getIdentifiers());
		publisher = Publisher.fromFile(srcPath.resolve("publisher-pragprog.hjson"));
		logbook.add(publisher.getIdentifiers());
//		Company company = Company.fromFile(srcPath.resolve("projects-github.hjson"));
//		engine.add(publisher.getIdentifiers());
	}


	public void execute(Command command, String identifier, String note) {
		if (! logbook.isKnownIdentifier(identifier)) {
			throw new UnknownIdentifierException(identifier); 
		}
//		logbook.execute(command, identifier, note);
		throw new RuntimeException("Not yet implemented...");
	}

	public void execute(Command command, String identifier) {
		if (! logbook.isKnownIdentifier(identifier)) {
			throw new UnknownIdentifierException(identifier); 
		}
//		logbook.execute(command, identifier);
		throw new RuntimeException("Not yet implemented...");
	}

}

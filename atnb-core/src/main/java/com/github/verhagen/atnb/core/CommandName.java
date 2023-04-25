package com.github.verhagen.atnb.core;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CommandName {
	// INIT create a fresh Activity Tracker Event environment.
	// Both ADD and CREATE will create an Identifier in the IdentifierCatalog
	// START should never do so, as it should only use available id's (prevents typo's)
	// FINISH kind of closes the item, so search for current active items, it will no longer appear
	// ARCHIVE similar to Finish, but finish for a single item. Archive, just freezes the current state, but never find any for active items.
	ADD       // Add something existing, like a book, or a project. Probably create / updates some index with the new entry.
	, CREATE  // Create something new, so documents are added (based on templates)
	, START   // Start time tracking (which actually means: reading / developing / cooking, or whatever)
	, STOP    // Stop time tracking
	, FINISH  // Finish reading a book, or cooking a meal, or working on an issue
	;

	public static CommandName getCommand(String command) {
		for (CommandName cName : CommandName.values()) {
			if (cName.name().equalsIgnoreCase(command)) {
				return cName;
			}
		}
		throw new IllegalArgumentException("Argumnt 'command' with value '" + command + "', is not a known CommandName. Known CommandNames are: " 
				+ Arrays.asList(CommandName.values()).stream().map(cn -> cn.name().toLowerCase()).collect(Collectors.joining(", ")));
	}

}

package com.github.verhagen.atnb.core.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TaskIdentifier {
	private final List<String> identifiers;
	// If isUniqueIdentifierRequired is true, then this primary identifier, requires an unique child,
	// or the information (in the remainer) the create this unique child id.
	private final boolean isUniqueIdentifierRequired;
	private final List<String> taskCommands = new ArrayList<>();

	
	public TaskIdentifier(Builder bldr) {
		if (bldr.getIdentifiers().size() == 0) {
			throw new IllegalArgumentException("The 'Builder.getIdentifiers()' should at least contain 1 identifier.");
		}
		this.identifiers = bldr.getIdentifiers();
		this.taskCommands.addAll(Arrays.asList("help", "start", "stop", "finish"));
//		if (bldr.isStartStopPossible()) {
//			this.taskCommands.addAll(Arrays.asList("start", "stop, finish"));
//		}
		this.taskCommands.addAll(bldr.getCommands());
		this.isUniqueIdentifierRequired = ! bldr.getCommands().isEmpty();
	}


	public List<String> getIdentifiers() {
		return identifiers;
	}
	public String getIdentifier() {
		return identifiers.get(0);
	}
	

	public boolean isUniqueIdentifierRequired() {
		return isUniqueIdentifierRequired;
	}


	public List<String> getTaskCommands() {
		return taskCommands;
	}

	public boolean isKnownTaskCommand(String command) {
		return taskCommands.contains(command);
	}



	public static class Builder {
		private List<String> identifiers = new ArrayList<>();
		private boolean isStartStopPossible;
		private List<String> commands = new ArrayList<>();

		public TaskIdentifier create( ) {
			return new TaskIdentifier(this);
		}


		public List<String> getIdentifiers() {
			return identifiers;
		}

		public Builder addTaskIdentifiers(List<String> identifiers) {
			identifiers.forEach(i -> addTaskIdentifier(i));
			return this;
		}
		public Builder addTaskIdentifier(String identifier) {
			String identifierCln = StringUtils.trimToNull(identifier);
			if (identifierCln == null) {
				return this;
			}
			identifierCln.toLowerCase().replace(" ", "-");
			this.identifiers.add(identifier);
			return this;
		}


		public List<String> getCommands() {
			return commands == null ? Collections.emptyList() : commands;
		}
		public Builder addAdditionalCommands(String... commands) {
			addAdditionalCommands(Arrays.asList(commands));
			return this;
		}
		public Builder addAdditionalCommands(List<String> commands) {
			if (commands == null || commands.isEmpty()) {
				return this;
			}
			List<String> commandsCln = new ArrayList<>();
			for (String command : commands)  {
				String commandCln = StringUtils.trimToNull(command);
				if (commandCln == null) {
					continue;
				}
				commandsCln.add(commandCln.toLowerCase().replace(" ", "-"));
			}
			this.commands.addAll(commandsCln);
//			this.commands.addAll(commands.stream()
//					.map(c -> StringUtils.trimToNull(c))
//					.filter(c -> c != null)
//					.map(c -> c.toLowerCase()
//							.replace(" ", "-"))
//					.collect(Collectors.toList()));
			return this;
		}


		public boolean isStartStopPossible() {
			return isStartStopPossible;
		}

		public Builder isStartStopPossible(boolean isStartStopPossible) {
			this.isStartStopPossible = isStartStopPossible;
			return this;
		}
		
	}
}

package nl.verhagen.atnb.command.task;

import java.util.Arrays;

import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfiguration;

public class IssueTask extends AbstractTask {

	public IssueTask(ActivityTrackerEventConfiguration atEventCfg, IssueTaskConfiguration taskConfiguration) {
		super(taskConfiguration, atEventCfg, createTaskIdentifier("issue", Arrays.asList("create")));
	}

	@Override
	public void execute(String identifier, String command, String text) {
		// TODO [2023.04.22 TV] Add implementation
		throw new RuntimeException("To be implemented...");
	}

}

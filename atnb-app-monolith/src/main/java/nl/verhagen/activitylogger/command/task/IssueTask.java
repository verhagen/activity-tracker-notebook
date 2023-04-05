package nl.verhagen.activitylogger.command.task;

import java.util.Arrays;

import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;

public class IssueTask extends AbstractTask {

	public IssueTask(ActivityTrackerEventConfiguration activityEventCfg, IssueTaskConfiguration taskConfiguration) {
		super(taskConfiguration, activityEventCfg, createTaskIdentifier("issue", Arrays.asList("create")));
	}

	@Override
	public void execute(String identifier, String command, String text) {
		throw new RuntimeException("To be implemented...");
	}

}
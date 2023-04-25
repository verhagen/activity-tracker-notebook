package com.github.verhagen.atnb.issue.task;

import java.util.Arrays;

import com.github.verhagen.atnb.core.task.AbstractTask;
import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;

public class IssueTask extends AbstractTask {

	public IssueTask(ActivityTrackerEventConfig atEventCfg, IssueTaskConfig taskConfiguration) {
		super(taskConfiguration, atEventCfg, createTaskIdentifier("issue", Arrays.asList("create")));
	}

	@Override
	public void execute(String identifier, String command, String text) {
		// TODO [2023.04.22 TV] Add implementation
		throw new RuntimeException("To be implemented...");
	}

}

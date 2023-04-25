package com.github.verhagen.atnb.core.task;


import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;

public class BaseTask extends AbstractTask {
	

	public BaseTask(ActivityTrackerEventConfig atEventCfg, BaseAbstractTaskConfig baseTaskCfg) {
		super(baseTaskCfg, atEventCfg, createTaskIdentifier(baseTaskCfg.getTaskIdentifiers(), null));
	}

	@Override
	public void execute(String identifier, String command, String text) {
		throw new RuntimeException("To be implemented...");
	}

}

package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfig;

public class BaseTask extends AbstractTask {
	

	public BaseTask(ActivityTrackerEventConfig atEventCfg, BaseTaskConfig baseTaskCfg) {
		super(baseTaskCfg, atEventCfg, createTaskIdentifier(baseTaskCfg.getTaskIdentifiers(), null));
	}

	@Override
	public void execute(String identifier, String command, String text) {
		throw new RuntimeException("To be implemented...");
	}

}

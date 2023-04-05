package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;

public class BaseTask extends AbstractTask {
	

	public BaseTask(ActivityTrackerEventConfiguration activityEventCfg, BaseTaskConfiguration baseTaskCfg) {
		super(baseTaskCfg, activityEventCfg, createTaskIdentifier(baseTaskCfg.getTaskIdentifiers(), null));
	}

	@Override
	public void execute(String identifier, String command, String text) {
		throw new RuntimeException("To be implemented...");
	}

}

package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.activitylogger.command.domain.TaskIdentifier;

public abstract class RepositoryTask extends AbstractTask {

	
	public RepositoryTask(ActivityTrackerEventConfiguration activityEventCfg, RepositoryTaskConfiguration repoTaskCfg
			, TaskIdentifier taskIdentifier) {
		super(repoTaskCfg, activityEventCfg, taskIdentifier);
	}

}

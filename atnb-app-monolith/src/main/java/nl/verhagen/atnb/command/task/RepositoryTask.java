package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.atnb.command.domain.TaskIdentifier;

public abstract class RepositoryTask extends AbstractTask {

	
	public RepositoryTask(ActivityTrackerEventConfiguration activityEventCfg, RepositoryTaskConfiguration repoTaskCfg
			, TaskIdentifier taskIdentifier) {
		super(repoTaskCfg, activityEventCfg, taskIdentifier);
	}

}

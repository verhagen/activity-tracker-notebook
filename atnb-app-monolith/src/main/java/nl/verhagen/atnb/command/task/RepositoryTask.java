package nl.verhagen.atnb.command.task;

import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfig;
import nl.verhagen.atnb.command.domain.TaskIdentifier;

public abstract class RepositoryTask extends AbstractTask {

	
	public RepositoryTask(ActivityTrackerEventConfig atEventCfg, RepositoryTaskConfig repoTaskCfg
			, TaskIdentifier taskIdentifier) {
		super(repoTaskCfg, atEventCfg, taskIdentifier);
	}

}

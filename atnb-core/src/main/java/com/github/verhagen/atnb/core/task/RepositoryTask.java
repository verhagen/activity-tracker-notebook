package com.github.verhagen.atnb.core.task;

import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;
import com.github.verhagen.atnb.core.domain.TaskIdentifier;

public abstract class RepositoryTask extends AbstractTask {

	
	public RepositoryTask(ActivityTrackerEventConfig atEventCfg, RepositoryAbstractTaskConfig repoTaskCfg
			, TaskIdentifier taskIdentifier) {
		super(repoTaskCfg, atEventCfg, taskIdentifier);
	}

}

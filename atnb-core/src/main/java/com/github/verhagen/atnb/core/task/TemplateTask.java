package com.github.verhagen.atnb.core.task;

import java.util.Arrays;
import java.util.Collection;

import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;
import com.github.verhagen.atnb.core.domain.TaskIdentifier;

public abstract class TemplateTask extends AbstractTask {

	public TemplateTask(ActivityTrackerEventConfig atEventCfg,
                        TemplateAbstractTaskConfig tempTaskCfg, TaskIdentifier taskIdentifier) {
		this(atEventCfg, tempTaskCfg, Arrays.asList(taskIdentifier));
	}

	public TemplateTask(ActivityTrackerEventConfig atEventCfg,
                        TemplateAbstractTaskConfig tempTaskCfg, Collection<TaskIdentifier> taskIdentifierColl) {
		super(tempTaskCfg, atEventCfg, taskIdentifierColl);
	}

}

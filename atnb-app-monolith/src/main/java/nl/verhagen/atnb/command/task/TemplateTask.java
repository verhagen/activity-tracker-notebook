package nl.verhagen.atnb.command.task;

import java.util.Arrays;
import java.util.Collection;

import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfig;
import nl.verhagen.atnb.command.domain.TaskIdentifier;

public abstract class TemplateTask extends AbstractTask {

	public TemplateTask(ActivityTrackerEventConfig atEventCfg,
						TemplateTaskConfig tempTaskCfg, TaskIdentifier taskIdentifier) {
		this(atEventCfg, tempTaskCfg, Arrays.asList(taskIdentifier));
	}

	public TemplateTask(ActivityTrackerEventConfig atEventCfg,
						TemplateTaskConfig tempTaskCfg, Collection<TaskIdentifier> taskIdentifierColl) {
		super(tempTaskCfg, atEventCfg, taskIdentifierColl);
	}

}

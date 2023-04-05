package nl.verhagen.activitylogger.command.task;

import java.util.Arrays;
import java.util.Collection;

import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.activitylogger.command.domain.TaskIdentifier;

public abstract class TemplateTask extends AbstractTask {

	public TemplateTask(ActivityTrackerEventConfiguration activityEventCfg,
			TemplateTaskConfiguration tempTaskCfg, TaskIdentifier taskIdentifier) {
		this(activityEventCfg, tempTaskCfg, Arrays.asList(taskIdentifier));
	}

	public TemplateTask(ActivityTrackerEventConfiguration activityEventCfg,
			TemplateTaskConfiguration tempTaskCfg, Collection<TaskIdentifier> taskIdentifierColl) {
		super(tempTaskCfg, activityEventCfg, taskIdentifierColl);
	}

}

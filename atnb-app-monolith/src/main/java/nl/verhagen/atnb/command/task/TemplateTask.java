package nl.verhagen.atnb.command.task;

import java.util.Arrays;
import java.util.Collection;

import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.atnb.command.domain.TaskIdentifier;

public abstract class TemplateTask extends AbstractTask {

	public TemplateTask(ActivityTrackerEventConfiguration atEventCfg,
                        TemplateTaskConfiguration tempTaskCfg, TaskIdentifier taskIdentifier) {
		this(atEventCfg, tempTaskCfg, Arrays.asList(taskIdentifier));
	}

	public TemplateTask(ActivityTrackerEventConfiguration atEventCfg,
			TemplateTaskConfiguration tempTaskCfg, Collection<TaskIdentifier> taskIdentifierColl) {
		super(tempTaskCfg, atEventCfg, taskIdentifierColl);
	}

}

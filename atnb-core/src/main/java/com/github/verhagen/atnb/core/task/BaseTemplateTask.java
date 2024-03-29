package com.github.verhagen.atnb.core.task;

import com.github.verhagen.atnb.core.domain.TaskIdentifier;
import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;

import java.util.Arrays;
import java.util.Collection;


public class BaseTemplateTask extends TemplateTask {

	public BaseTemplateTask(ActivityTrackerEventConfig atEventCfg, BaseTemplateAbstractTaskConfig baseTempTaskCfg) {
		super(atEventCfg, baseTempTaskCfg, createTaskIdentifierCollection());
	}

//    add(knownActivities, "issue", Arrays.asList("create"));
//    add(knownActivities, "opportunity", Arrays.asList("create"));
//    add(knownActivities, "poc", Arrays.asList("create"));
//    add(knownActivities, "idea", Arrays.asList("create"));
//    add(knownActivities, "white-board", Arrays.asList("create"));
	private static Collection<TaskIdentifier> createTaskIdentifierCollection() {
		return Arrays.asList(
				new TaskIdentifier.Builder()
						.addTaskIdentifier("issue")
						.addAdditionalCommands("create")
						.create()
				, new TaskIdentifier.Builder()
						.addTaskIdentifier("opportunity")
						.addAdditionalCommands("create")
						.create()
				, new TaskIdentifier.Builder()
						.addTaskIdentifier("poc")
						.addAdditionalCommands("create")
						.create()
				, new TaskIdentifier.Builder()
						.addTaskIdentifier("idea")
						.addAdditionalCommands("create")
						.create()
				, new TaskIdentifier.Builder()
						.addTaskIdentifier("white-board")
						.addAdditionalCommands("create")
						.create()
				);
	}

	
	@Override
	public void execute(String identifier, String command, String text) {
		// TODO Auto-generated method stub
		
	}

}

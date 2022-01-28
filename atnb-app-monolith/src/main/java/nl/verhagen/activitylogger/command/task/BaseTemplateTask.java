package nl.verhagen.activitylogger.command.task;

import java.util.Arrays;
import java.util.Collection;

import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.activitylogger.command.domain.TaskIdentifier;

public class BaseTemplateTask extends TemplateTask {

	public BaseTemplateTask(ActivityTrackerEventConfiguration activityEventCfg, BaseTemplateTaskConfiguration baseTempTaskCfg) {
		super(activityEventCfg, baseTempTaskCfg, createTaskIdentifierCollection());
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

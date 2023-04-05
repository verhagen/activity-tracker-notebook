package nl.verhagen.activitylogger.command.task;

import java.util.Arrays;

import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;

/**
 * This is a special task, as all other tasks will get registered underneath this task <i>community</i>.
 * This is done, so every Notebook, can have it's own tasks correctly associated with them, as the community
 * is the top level identification for each Notebook.
 * 
 * Another difference is, that communities can have sub / super community instances in them.
 * This makes it possible to have things like an organisation with departments, with teams.
 * 
 * The top level community is made during initialization.
 */
public class CommunityTask extends AbstractTask {

	public CommunityTask(TaskConfiguration TaskCfg, ActivityTrackerEventConfiguration activityEventCfg) {
		super(TaskCfg, activityEventCfg, createTaskIdentifier("community", Arrays.asList("add")));
	}


	@Override
	public void execute(String identifier, String command, String text) {
		// TODO Auto-generated method stub
		
	}

}

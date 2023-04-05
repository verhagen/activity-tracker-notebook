package nl.verhagen.activitylogger.command.task;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.activitylogger.command.CommandException;
import nl.verhagen.activitylogger.command.domain.ActivityEvent;
import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;

public class OpportunityTask extends TemplateTask {
	private Logger logger = LoggerFactory.getLogger(OpportunityTask.class);
	private OpportunityTaskConfiguration opportunityTaskCfg;

	public OpportunityTask(ActivityTrackerEventConfiguration activityEventCfg, OpportunityTaskConfiguration opportunityTaskCfg) {
		super(activityEventCfg, opportunityTaskCfg, createTaskIdentifier("opportunity", Arrays.asList("create")));
		this.opportunityTaskCfg = opportunityTaskCfg;
	}

	public void execute(String identifier, String command, String text) {
		Map<String, Object> fields = extractFieldsAsObjects(text);
		String[] idParts = identifier.split("\\.");
		if (idParts.length == 1) {
			idParts = createIdentifier(fields);
		}
		execute(idParts, command, fields);
	}

	private String[] createIdentifier(Map<String, Object> fields) {
		Map<String, String> fieldValuesAsStr = convertToStrings(fields);
		return Arrays.asList(
				"opportunity"
				, Arrays.asList(
						fieldValuesAsStr.get("date")
						, "-", fieldValuesAsStr.get("agent").toLowerCase().replace(" ", "-")
						, "_", fieldValuesAsStr.get("organisation").toLowerCase().replace(" ", "-")
						, "_", fieldValuesAsStr.get("role").toLowerCase().replace(" ", "-")
				).stream().collect(Collectors.joining())
		).toArray(new String[0]);
	}

	public void execute(String[] identifierPath, String command, Map<String, Object> fields) {
		List<String> foundIdentifiers = opportunityTaskCfg.getIdRegistery().search(identifierPath);
		if (foundIdentifiers.size() == 0) {
			opportunityTaskCfg.getIdRegistery().add(identifierPath);
		}
		else if (foundIdentifiers.size() > 1) {
			throw CommandException.noUniqueIdentifier(identifierPath, foundIdentifiers);
		}
		ActivityEvent activityEvent = createActivityEventBuilder().identity(identifierPath).command(command).fields(fields).create();
		switch (command) {
			case "start":
				handle(activityEvent);
				break;
			case "stop":
				handle(activityEvent);
				break;
			case "create":
				create(activityEvent);
				break;
			case "finish":
				handle(activityEvent);
				break;
			default:
				logger.error("No command handling available for '" + command + "' with activity event '" + activityEvent + "'");
				break;
		}
		
	}

	private void create(ActivityEvent activityEvent) {
		// TODO [2022.01.20 TV] Add implementation
		throw new RuntimeException("Implement call to OpportunityContentCreator...");
	}

}

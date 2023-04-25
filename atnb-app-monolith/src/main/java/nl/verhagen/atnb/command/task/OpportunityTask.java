package nl.verhagen.atnb.command.task;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.verhagen.atnb.core.task.TemplateTask;
import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.domain.ActivityTrackerEvent;
import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.atnb.command.CommandException;

public class OpportunityTask extends TemplateTask {
	private Logger logger = LoggerFactory.getLogger(OpportunityTask.class);
	private OpportunityAbstractTaskConfig opportunityTaskCfg;

	public OpportunityTask(ActivityTrackerEventConfig atEventCfg, OpportunityAbstractTaskConfig opportunityTaskCfg) {
		super(atEventCfg, opportunityTaskCfg, createTaskIdentifier("opportunity", Arrays.asList("create")));
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
		List<String> foundIdentifiers = opportunityTaskCfg.getIdCatalog().search(
				Arrays.stream(identifierPath).collect(Collectors.joining(IdentifierCatalog.BY_DOT)));
		if (foundIdentifiers.size() == 0) {
			getIdentifierCatalog().add(Arrays.asList(identifierPath));
		}
		else if (foundIdentifiers.size() > 1) {
			throw CommandException.noUniqueIdentifier(identifierPath, foundIdentifiers);
		}
		ActivityTrackerEvent atEvent = createActivityEventBuilder().identity(identifierPath).command(command).fields(fields).create();
		switch (command) {
			case "start":
				handle(atEvent);
				break;
			case "stop":
				handle(atEvent);
				break;
			case "create":
				create(atEvent);
				break;
			case "finish":
				handle(atEvent);
				break;
			default:
				logger.error("No command handling available for '" + command + "' with activity tracker event '" + atEvent + "'");
				break;
		}
		
	}

	private void create(ActivityTrackerEvent activityTrackerEvent) {
		// TODO [2022.01.20 TV] Add implementation
		//throw new RuntimeException("Implement call to OpportunityContentCreator...");
		logger.info("Execute opportunity create ...  This  should add a create in the opportunity folder...");
	}

}

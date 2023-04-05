package nl.verhagen.activitylogger.command;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.activitylogger.command.domain.ActivityEvent;
import nl.verhagen.activitylogger.command.domain.Listener;
import nl.verhagen.activitylogger.command.domain.TaskIdentifier;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AppRunner {
    private static Logger log = LoggerFactory.getLogger(AppRunner.class);
    private static Logger actCmd = LoggerFactory.getLogger("activity-command");

    private Pattern jiraIssue = Pattern.compile("\\w+-\\d+|\\d+"); //  ^\d+$|

	// FIXME [2023.04.05 TV] Remove hard coded path
    private Path issueTargetPath = Paths.get("C:\\Users\\tjve\\Documents\\notes\\issue-generated");
    private JiraIssueFactoryConfiguration cfg = new JiraIssueFactoryConfiguration.Builder()
            .addOrganisation("cs")
            .addProject("lima")
            .addIssuePrefix("LPBUNI")
            .addIssuePath(issueTargetPath)
            .addIssueServer(URI.create("https://jira.int.cipal.be/"))
            .create();
    private final AppRunnerConfiguration appRunnerCfg;
    private IssueFactory issueFactory = new JiraIssueFactory(cfg);
    private JiraIssueTemplate issueTemplate = new JiraIssueTemplate(new JiraIssueTemplateConfiguration(issueTargetPath));
    private final Set<TaskIdentifier> knownActivities = new HashSet<>();

    public enum Commands {
        START
        , STOP
        , CREATE
    }

    public AppRunner(AppRunnerConfiguration appRunnerCfg) {
    	this.appRunnerCfg = appRunnerCfg;

        // FIXME [2023.04.05 TV] Remove hard coded creating of TaskIdentifiers and Commands
    	// -------------
    	// TO BE REMOVED
    	// -------------

        add(knownActivities, "link", Arrays.asList("add"), false);
        add(knownActivities, "article", Arrays.asList("add"));
        add(knownActivities, "book", Arrays.asList("add"));
        add(knownActivities, "project", Arrays.asList("add"));

//        add(knownActivities, "issue", Arrays.asList("create"));
//        add(knownActivities, "opportunity", Arrays.asList("create"));
//        add(knownActivities, "poc", Arrays.asList("create"));
//        add(knownActivities, "idea", Arrays.asList("create"));
//        add(knownActivities, "white-board", Arrays.asList("create"));
    }


	private void add(Set<TaskIdentifier> knownIdentifiers, String primaryIdentifier) {
		add(knownIdentifiers, primaryIdentifier, null);
	}
	private void add(Set<TaskIdentifier> knownIdentifiers, String primaryIdentifier, List<String> commands) {
		add(knownIdentifiers, primaryIdentifier, commands, true);
	}
	private void add(Set<TaskIdentifier> knownIdentifiers, String primaryIdentifier, List<String> commands, boolean isStartStopPossible) {
		knownIdentifiers.add(new TaskIdentifier.Builder()
				.addTaskIdentifier(primaryIdentifier)
				.addAdditionalCommands(commands)
				.isStartStopPossible(isStartStopPossible)
				.create());
	}


	public void addListener(Listener<ActivityEvent> listener) {
		appRunnerCfg.getTaskHandlers().stream().forEach(th -> th.addListener(listener));
	}


	public void execute(String identifier, String command) {
        execute(LocalDateTime.now(), identifier, command);
    }
    public void execute(LocalDateTime timestamp, String identifier, String command) {
        execute(timestamp, identifier, command, null);
    }
    public void execute(String identifier, String command, String remainer) {
        execute(LocalDateTime.now(), identifier, command, remainer);
    }
    public void execute(LocalDateTime timestamp, String identifier, String command, String remainer) {
    	if (! appRunnerCfg.containsKnownTaskIdentifier(identifier)) {
    		throw createIllegalArgumentException("Argument 'identifier' with value '" + identifier + "' does not contain a known task identifier.");
    	}
    	if (! appRunnerCfg.isKnownCommand(identifier, command)) {
    		throw createIllegalArgumentException("Argument 'command' with value '" + command + "' is not a known command of the "
    				+ appRunnerCfg.getTaskHandlers(identifier).getClass().getSimpleName() + ". Known commands are: "+ appRunnerCfg.getTaskHandlers(identifier).getCommands() + ".");
    	}

    	appRunnerCfg.getTaskHandlers(identifier).execute(identifier, command, remainer);
    	System.out.println();
    }


    private AppRunnerException createIllegalArgumentException(String message) {
		return new AppRunnerException(new IllegalArgumentException(message));
	}


	private String asIdentifier(List<String> identifier) {
        return identifier.stream().collect(Collectors.joining("."));
    }


	// TODO [2021.01.19] Polish implementation
	public boolean isKnownTaskCommand(String identifier, String command) {
		String identifierCln = StringUtils.trimToNull(identifier);
		String commandCln = StringUtils.trimToNull(command);
		if (identifierCln == null && commandCln != null) {
			return false;
		}
		identifierCln = identifierCln.toLowerCase();
		commandCln = commandCln.toLowerCase();
		for (TaskIdentifier primaryIdentifier : knownActivities) {
			if (primaryIdentifier.getIdentifier().equals(identifierCln)) {
				return primaryIdentifier.getTaskCommands().contains(commandCln);
			}
		}
		return false;
		
//		String[] idPath = identifier.split("\\.");
//		Optional<Stream<PrimaryIdentifier>> optional = Arrays.asList(idPath).stream().map(i -> knownActivities.stream().filter(pi -> pi.getIdentifier().equals(i))).findFirst();
//		if (! optional.isPresent()) {
//			return false;
//		}
//		return optional.get().filter(pi -> pi.getTaskCommands().contains(command)).findFirst().isPresent();
	}

	// TODO [2021.01.19] Polish implementation
	public boolean isKnownTaskIdentifier(String identifier) {
		String idClean = StringUtils.trimToNull(identifier);
		if (idClean == null) {
			return false;
		}
		idClean = idClean.toLowerCase();
		for (TaskIdentifier primaryIdentifier : knownActivities) {
			if (primaryIdentifier.getIdentifier().equals(idClean)) {
				return true;
			}
		}
		return false;
//		String[] idPath = identifier.split("\\.");
//		Optional<Stream<PrimaryIdentifier>> optional = Arrays.asList(idPath).stream().map(i -> knownActivities.stream().filter(pi -> pi.getIdentifier().equals(i))).findFirst();
//		return optional;
	}

}

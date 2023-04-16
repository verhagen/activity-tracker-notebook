package nl.verhagen.activitylogger.command;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.activitylogger.command.task.AbstractTask;
import nl.verhagen.activitylogger.command.task.BookTask;
import nl.verhagen.activitylogger.command.task.BookTaskConfiguration;
import nl.verhagen.activitylogger.command.task.IssueTask;
import nl.verhagen.activitylogger.command.task.IssueTaskConfiguration;
import nl.verhagen.activitylogger.command.task.OpportunityTask;
import nl.verhagen.activitylogger.command.task.OpportunityTaskConfiguration;

public class AppRunnerConfiguration {
	public enum DataLocation {
        DATA_HOME
        , NOTES
        , ISSUES
    }

	private IdentifierCatalog idCatalog = new IdentifierCatalogImpl();
	private ActivityTrackerEventConfiguration activityEventCfg = new ActivityTrackerEventConfiguration("miss-piggy");
	private BookTaskConfiguration bookTaskCfg = new BookTaskConfiguration(idCatalog, URI.create("https://www.manning.com/books/"));
	private List<AbstractTask> taskHandlers  = Arrays.asList(
			new BookTask(activityEventCfg, bookTaskCfg)
			, new OpportunityTask(activityEventCfg, new OpportunityTaskConfiguration(idCatalog))
			, new IssueTask(activityEventCfg, new IssueTaskConfiguration(idCatalog))
	);
	private Map<String, AbstractTask> taskHandlerMap;

    private static final Map<DataLocation, String> dataLocations = new HashMap<>();

    static {
        dataLocations.put(DataLocation.DATA_HOME, "");
        dataLocations.put(DataLocation.NOTES, ".");
        dataLocations.put(DataLocation.ISSUES, "target/issue");
    }

    
    public AppRunnerConfiguration() {
    	taskHandlerMap = taskHandlers.stream().collect(Collectors.toMap(th -> th.getTaskIdentifier(), Function.identity()));
	}

	public void getPath(DataLocation dataLoc) {

//        return Paths.get();
    }

    public List<String> getIdentifiers() {
        return Arrays.asList(
                "company.cipal-schaubroeck.project.lima.issue.LPBUNI-25918"
                , ""
                , ""
                , ""
                , ""
        );
    }


    public Set<String> getTaskIdentifiers() {
    	return taskHandlerMap.keySet();
    }

    public AbstractTask getTaskHandlers(String identifier) {
    	return getTaskHandlers(identifier.split("\\."));
    }
    /**
     * Scan the IdentiefierPath from back to front, return the first matching TaskHandler that has the matching TaskIdentiefier name.
     * @param identifierPath
     * @return
     */
    public AbstractTask getTaskHandlers(String[] identifierPath) {
    	for (int index = identifierPath.length -1; index > -1; index--) {
    		if (taskHandlerMap.containsKey(identifierPath[index])) {
    			return taskHandlerMap.get(identifierPath[index]);
    		}
    	}
    	throw new IllegalArgumentException("Error");
    }

    public List<AbstractTask> getTaskHandlers() {
    	return taskHandlers;
    }

	public boolean containsKnownTaskIdentifier(String identifier) {
		return containsKnownTaskIdentifier(identifier.split("\\."));
	}
	public boolean containsKnownTaskIdentifier(String[] identifierPath) {
    	for (int index = identifierPath.length -1; index > -1; index--) {
    		if (taskHandlerMap.containsKey(identifierPath[index])) {
    			return true;
    		}
    	}
		return false;
	}

	public boolean isKnownCommand(String identifier, String command) {
		return isKnownCommand(identifier.split("\\."), command);
	}
	public boolean isKnownCommand(String[] identifierPath, String command) {
		for (int index = identifierPath.length -1; index > -1; index--) {
			if (taskHandlerMap.keySet().contains(identifierPath[index])) {
				if (taskHandlerMap.get(identifierPath[index]).isKnownCommand(command)) {
					return true;
				}
			}
		}
		return false;
	}
}

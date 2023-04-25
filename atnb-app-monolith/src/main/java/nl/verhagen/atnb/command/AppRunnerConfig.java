package nl.verhagen.atnb.command;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.domain.IdentifierCatalogImpl;
import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;
import com.github.verhagen.atnb.core.task.AbstractTask;
import com.github.verhagen.atnb.book.task.BookTask;
import com.github.verhagen.atnb.book.task.BookTaskConfig;
import com.github.verhagen.atnb.issue.task.IssueTaskConfig;
import com.github.verhagen.atnb.issue.task.IssueTask;
import com.github.verhagen.atnb.opportunity.task.OpportunityTaskConfig;
import com.github.verhagen.atnb.opportunity.task.OpportunityTask;

public class AppRunnerConfig {

	private IdentifierCatalog idCatalog = new IdentifierCatalogImpl();
	private ActivityTrackerEventConfig atEventCfg;
	private BookTaskConfig bookTaskCfg = new BookTaskConfig(idCatalog, URI.create("https://www.manning.com/books/"));

	// TODO [2023.04.05 TV] Make loading of task handler dynamic
	private List<AbstractTask> taskHandlers  = Arrays.asList(
			new BookTask(atEventCfg, bookTaskCfg)
			, new OpportunityTask(atEventCfg, new OpportunityTaskConfig(idCatalog))
			, new IssueTask(atEventCfg, new IssueTaskConfig(idCatalog))
	);
	private Map<String, AbstractTask> taskHandlerMap;
    
    public AppRunnerConfig() {
    	taskHandlerMap = taskHandlers.stream().collect(Collectors.toMap(th -> th.getTaskIdentifier(), Function.identity()));
		atEventCfg = new ActivityTrackerEventConfig("miss-piggy", "london");
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

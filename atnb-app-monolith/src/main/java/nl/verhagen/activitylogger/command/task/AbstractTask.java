package nl.verhagen.activitylogger.command.task;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.activitylogger.command.IdentifierRegistery;
import nl.verhagen.activitylogger.command.TextField;
import nl.verhagen.activitylogger.command.domain.ActivityEvent;
import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;
import nl.verhagen.activitylogger.command.domain.ActivityEventImpl;
import nl.verhagen.activitylogger.command.domain.Listener;
import nl.verhagen.activitylogger.command.domain.TaskIdentifier;

public abstract class AbstractTask {
	private Logger logger = LoggerFactory.getLogger(AbstractTask.class);
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

	protected final Map<String, TaskIdentifier> taskIdentifiers = new HashMap<>();
	private final ActivityTrackerEventConfiguration activityEventCfg;
	private final List<Listener<ActivityEvent>> listeners = new LinkedList<>();
	private final TaskConfiguration taskCfg;


	public AbstractTask(TaskConfiguration taskConfiguration, ActivityTrackerEventConfiguration activityEventCfg
			, TaskIdentifier taskIdentifier) {
		this(taskConfiguration, activityEventCfg, Arrays.asList(taskIdentifier));
	}


	public AbstractTask(TaskConfiguration TaskCfg, ActivityTrackerEventConfiguration activityEventCfg
			, Collection<TaskIdentifier> taskIdentifierColl) {
		this.taskCfg = TaskCfg;
		this.activityEventCfg = activityEventCfg;
		for (TaskIdentifier taskIdentifier : taskIdentifierColl) {
			for (String taskIdentiefierStr : taskIdentifier.getIdentifiers()) {
				this.taskIdentifiers.put(taskIdentiefierStr, taskIdentifier); 
			}
		}
	}


	protected static TaskIdentifier createTaskIdentifier(String identifier, List<String> commands) {
		return new TaskIdentifier.Builder()
				.addTaskIdentifier(identifier)
				.addAdditionalCommands(commands)
				.create();
	}

	protected static TaskIdentifier createTaskIdentifier(List<String> identifiers, List<String> commands) {
		return new TaskIdentifier.Builder()
				.addTaskIdentifiers(identifiers)
				.addAdditionalCommands(commands)
				.create();
	}


	public abstract void execute(String identifier, String command, String text);


	public void addListener(Listener<ActivityEvent> listener) {
		listeners.add(listener);
	}

	protected void handle(ActivityEvent activityEvent) {
		listeners.stream().forEach(l -> l.update(activityEvent));
		logger.info(
				activityEvent.getTimeStamp().format(formatter)
				+ "  " + activityEvent.getIdentifier()
				+ "  " + activityEvent.getCommand()
				+ (activityEvent.getFields() != null 
						? "  " + activityEvent.getFields().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "))
						: "")
		);
	}

	public String getTaskIdentifier() {
		if (taskIdentifiers.size() != 1) {
			throw new RuntimeException("The " + this.getClass().getSimpleName() + " contains multiple TaskIdentifiers: " + taskIdentifiers);
		}
		return taskIdentifiers.values().stream().findFirst().get().getIdentifier();
	}

	public boolean isKnownCommand(String command) {
		return taskIdentifiers.get(getTaskIdentifier()).isKnownTaskCommand(command);
	}

	public boolean isKnownCommand(String taskIdentifier, String command) {
		return taskIdentifiers.get(taskIdentifier).isKnownTaskCommand(command);
	}

	public List<String> getCommands() {
		return taskIdentifiers.get(getTaskIdentifier()).getTaskCommands();
	}


	public ActivityTrackerEventConfiguration getActivityEventCfg() {
		return activityEventCfg;
	}


	protected IdentifierRegistery getIdentifierRegistery() {
		return taskCfg.getIdRegistery();
	}

	protected Map<String, Object> extractFieldsAsObjects(String text) {
		String textCln = StringUtils.trimToNull(text);
		return (textCln == null) ? null : taskCfg.getTextFieldExtractor().extractAsObjects(textCln);
	}
	protected Map<String, String> extractFields(String text) {
		String textCln = StringUtils.trimToNull(text);
		return (textCln == null) ? null : taskCfg.getTextFieldExtractor().extract(textCln);
	}
	protected Map<String, String> convertToStrings(Map<String, Object> values) {
		return taskCfg.getTextFieldExtractor().convertToStrings(values);
	}
	protected Map<String, TextField> getTextField() {
		return taskCfg.getTextFieldExtractor().getTextFields();
	}

	protected boolean containsLink(Map<String, String> fields, String titleName, String uriName) {
		return fields.containsKey(titleName) && fields.containsKey(uriName);
	}


	protected ActivityEventImpl.Builder createActivityEventBuilder() {
		return new ActivityEventImpl.Builder(getActivityEventCfg());
	}

}

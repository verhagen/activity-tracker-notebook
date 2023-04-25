package com.github.verhagen.atnb.core.task;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.verhagen.atnb.core.domain.TaskIdentifier;
import com.github.verhagen.atnb.domain.*;
import com.github.verhagen.atnb.core.textfield.TextField;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractTask {
	private Logger logger = LoggerFactory.getLogger(AbstractTask.class);
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

	protected final Map<String, TaskIdentifier> taskIdentifiers = new HashMap<>();
	private final ActivityTrackerEventConfig atEventCfg;
	private final List<Listener<ActivityTrackerEvent>> listeners = new LinkedList<>();
	private final AbstractTaskConfig taskCfg;


	public AbstractTask(AbstractTaskConfig taskCfg, ActivityTrackerEventConfig atEventCfg
			, TaskIdentifier taskIdentifier) {
		this(taskCfg, atEventCfg, Arrays.asList(taskIdentifier));
	}


	public AbstractTask(AbstractTaskConfig taskCfg, ActivityTrackerEventConfig atEventCfg
			, Collection<TaskIdentifier> taskIdentifierColl) {
		this.taskCfg = taskCfg;
		this.atEventCfg = atEventCfg;
		for (TaskIdentifier taskIdentifier : taskIdentifierColl) {
			for (String taskIdentifierStr : taskIdentifier.getIdentifiers()) {
				this.taskIdentifiers.put(taskIdentifierStr, taskIdentifier);
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


	public void addListener(Listener<ActivityTrackerEvent> listener) {
		listeners.add(listener);
	}

	protected void handle(ActivityTrackerEvent activityTrackerEvent) {
		listeners.stream().forEach(l -> l.update(activityTrackerEvent));
		logger.info(
				activityTrackerEvent.getTimeStamp().format(formatter)
				+ "  " + activityTrackerEvent.getIdentifier()
				+ "  " + activityTrackerEvent.getCommand()
				+ (activityTrackerEvent.getFields() != null
						? "  " + activityTrackerEvent.getFields().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "))
						: "")
		);
	}

	public TaskIdentifier getTaskIdentifersPure() {
		if (taskIdentifiers.size() != 1) {
			throw new RuntimeException("The " + this.getClass().getSimpleName() + " contains multiple TaskIdentifiers: " + taskIdentifiers);
		}
		return taskIdentifiers.values().stream().findFirst().get();
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


	public ActivityTrackerEventConfig getAtEventCfg() {
		return atEventCfg;
	}


	protected IdentifierCatalog getIdentifierCatalog() {
		return taskCfg.getIdCatalog();
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


	protected ActivityTrackerEventImpl.Builder createActivityEventBuilder() {
		return new ActivityTrackerEventImpl.Builder(getAtEventCfg());
	}

}

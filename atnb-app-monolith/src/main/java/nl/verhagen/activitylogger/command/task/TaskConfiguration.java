package nl.verhagen.activitylogger.command.task;

import nl.verhagen.activitylogger.command.IdentifierRegistery;
import nl.verhagen.activitylogger.command.TextFieldExtractor;

public abstract class TaskConfiguration {
	private final IdentifierRegistery idRegistery;
	private final TextFieldExtractor textFieldExtractor;

	
	public TaskConfiguration(IdentifierRegistery idRegistery) {
		this(idRegistery, null);
	}
	public TaskConfiguration(IdentifierRegistery idRegistery, TextFieldExtractor textFieldExtractor) {
		if (idRegistery == null) {
			throw new IllegalArgumentException("Argument 'idRegistery' should not be null.");
		}
		this.idRegistery = idRegistery;
		this.textFieldExtractor = textFieldExtractor;
	}


	public IdentifierRegistery getIdRegistery() {
		return idRegistery;
	}


	public TextFieldExtractor getTextFieldExtractor() {
		return textFieldExtractor;
	}

}

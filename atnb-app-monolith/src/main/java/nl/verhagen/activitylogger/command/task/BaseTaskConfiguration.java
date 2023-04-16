package nl.verhagen.activitylogger.command.task;

import java.util.Arrays;
import java.util.List;
import nl.verhagen.activitylogger.command.IdentifierCatalog;

public class BaseTaskConfiguration extends TaskConfiguration {
	private static List<String> taskIdentifiers = Arrays.asList(
			"break"
			, "pause"
			, "coffee"
			, "tea"
			, "breakfast"
			, "lunch"
			, "dinner"
			, "supper"

			// Just for time beeing they've been added, but should be configurable
			, "time-registration" // alias: "time-reg"
			, "accountancy"
			, "administration"
			
			// Should be specific 'comm.<comm>.meeting.stand-up', when not specified, use current default organisation / team
			// Or be part of a 'comm.<comm>.comm.<comm>.meeting.stand-up' > lookup too which organisation this team belongs.
			, "stand-up"
			);


	public BaseTaskConfiguration(IdentifierCatalog idCatalog) {
		super(idCatalog);
	}


	public List<String> getTaskIdentifiers() {
		return taskIdentifiers;
	}

}

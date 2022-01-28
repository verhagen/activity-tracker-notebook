package nl.verhagen.activitylogger.command.task;

public class TaskHandlerCatalogConfiguration {
	private final AbstractTask rootTaskIdentifier;


	public TaskHandlerCatalogConfiguration(AbstractTask rootTaskIdentifier) {
		this.rootTaskIdentifier = rootTaskIdentifier;
	}


	public AbstractTask getRootTaskIdentifier() {
		return rootTaskIdentifier;
	}

}

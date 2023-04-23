package nl.verhagen.atnb.command.task;

public class TaskHandlerCatalogConfig {
	private final AbstractTask rootTaskIdentifier;


	public TaskHandlerCatalogConfig(AbstractTask rootTaskIdentifier) {
		this.rootTaskIdentifier = rootTaskIdentifier;
	}


	public AbstractTask getRootTaskIdentifier() {
		return rootTaskIdentifier;
	}

}

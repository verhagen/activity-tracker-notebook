package com.github.verhagen.atnb.core.task;

import com.github.verhagen.atnb.core.task.AbstractTask;

public class TaskHandlerCatalogConfig {
	private final AbstractTask rootTaskIdentifier;


	public TaskHandlerCatalogConfig(AbstractTask rootTaskIdentifier) {
		this.rootTaskIdentifier = rootTaskIdentifier;
	}


	public AbstractTask getRootTaskIdentifier() {
		return rootTaskIdentifier;
	}

}

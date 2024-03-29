package com.github.verhagen.atnb.core.task;

import com.github.verhagen.atnb.core.HierarchicalCatalog;

public class TaskHandlerCatalog {
	private final TaskHandlerCatalogConfig taskHandlerCatCfg;
	private final HierarchicalCatalog<AbstractTask> taskCatalog;
//	private final Map<AbstractTask> tasks = new ArrayList<>();


	public TaskHandlerCatalog(TaskHandlerCatalogConfig taskHandlerCatCfg) {
		this.taskHandlerCatCfg = taskHandlerCatCfg;
		taskCatalog = null; // FIXME
	}


	public void register(AbstractTask task) {
		register("to-be-fixed", task);
	}
	public void register(String parentTaskIdentifier, AbstractTask task) {
		task.getTaskIdentifier();
	}

	public void lookupTaskHandler(String identification) {
		lookupTaskHandler(identification.split("\\."));
	}

	public void lookupTaskHandler(String[] idPath) {
		for (int index = idPath.length -1; index > -1; index--) {
			
		}
	}

}

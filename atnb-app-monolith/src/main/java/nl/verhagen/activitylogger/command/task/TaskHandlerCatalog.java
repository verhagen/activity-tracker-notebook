package nl.verhagen.activitylogger.command.task;

import java.util.ArrayList;
import java.util.List;

import com.github.verhagen.activitylogbook.core.HierarchicalCatalog;

public class TaskHandlerCatalog {
	private final TaskHandlerCatalogConfiguration taskHandlerCatCfg;
//	private final HierarchicalCatalog<AbstractTask> taskCatalog;
//	private final Map<AbstractTask> tasks = new ArrayList<>();


	public TaskHandlerCatalog(TaskHandlerCatalogConfiguration taskHandlerCatCfg) {
		this.taskHandlerCatCfg = taskHandlerCatCfg;
//		tasks.
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

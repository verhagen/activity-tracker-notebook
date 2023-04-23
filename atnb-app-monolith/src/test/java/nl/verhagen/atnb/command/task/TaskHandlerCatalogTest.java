package nl.verhagen.atnb.command.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nl.verhagen.atnb.command.IdentifierCatalog;
import nl.verhagen.atnb.command.IdentifierCatalogMock;
import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfig;

public class TaskHandlerCatalogTest {

	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
			"first parameter value | second parameter value"
	})
	public void create(String first, String second) {
		IdentifierCatalog idReg = new IdentifierCatalogMock();
		CommunityTaskConfig communityTaskCfg = new CommunityTaskConfig(idReg);
		ActivityTrackerEventConfig activityEventCfg = new ActivityTrackerEventConfig("eddy-the-eagle", "london");
		CommunityTask task = new CommunityTask(communityTaskCfg, activityEventCfg);

		TaskHandlerCatalogConfig taskHandlerCatCfg = new TaskHandlerCatalogConfig(task);
		TaskHandlerCatalog taskHandlerCat = new TaskHandlerCatalog(taskHandlerCatCfg);
		
		taskHandlerCat.lookupTaskHandler("community");
	}

}

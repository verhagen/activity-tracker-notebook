package nl.verhagen.activitylogger.command.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nl.verhagen.activitylogger.command.IdentifierRegistery;
import nl.verhagen.activitylogger.command.IdentifierRegisteryMock;
import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;

public class TaskHandlerCatalogTest {

	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
			"first parameter value | second parameter value"
	})
	public void create(String first, String second) {
		IdentifierRegistery idReg = new IdentifierRegisteryMock();
		CommunityTaskConfiguration communityTaskCfg = new CommunityTaskConfiguration(idReg);
		ActivityTrackerEventConfiguration activityEventCfg = new ActivityTrackerEventConfiguration("eddy-the-eagle");
		CommunityTask task = new CommunityTask(communityTaskCfg, activityEventCfg);

		TaskHandlerCatalogConfiguration taskHandlerCatCfg = new TaskHandlerCatalogConfiguration(task);
		TaskHandlerCatalog taskHandlerCat = new TaskHandlerCatalog(taskHandlerCatCfg);
		
		taskHandlerCat.lookupTaskHandler("community");
	}
	
	

}

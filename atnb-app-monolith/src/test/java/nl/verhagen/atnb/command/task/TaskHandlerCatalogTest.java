package nl.verhagen.atnb.command.task;

import com.github.verhagen.atnb.core.task.TaskHandlerCatalog;
import com.github.verhagen.atnb.core.task.TaskHandlerCatalogConfig;
import com.github.verhagen.atnb.domain.IdentifierCatalog;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskHandlerCatalogTest {
	@Mock
	private IdentifierCatalog idCatalog;

	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
			"first parameter value | second parameter value"
	})
	public void create(String first, String second) {
		CommunityAbstractTaskConfig communityTaskCfg = new CommunityAbstractTaskConfig(idCatalog);
		ActivityTrackerEventConfig activityEventCfg = new ActivityTrackerEventConfig("eddy-the-eagle", "london");
		CommunityTask task = new CommunityTask(communityTaskCfg, activityEventCfg);

		TaskHandlerCatalogConfig taskHandlerCatCfg = new TaskHandlerCatalogConfig(task);
		TaskHandlerCatalog taskHandlerCat = new TaskHandlerCatalog(taskHandlerCatCfg);
		
		taskHandlerCat.lookupTaskHandler("community");
	}

}

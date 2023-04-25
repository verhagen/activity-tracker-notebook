package nl.verhagen.atnb.command.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nl.verhagen.atnb.command.AppException;
import nl.verhagen.atnb.command.domain.ActivityTrackerEventConfig;

@ExtendWith(MockitoExtension.class)
public class BaseTaskTest {
	@Mock
	private IdentifierCatalog idCatalog;
	private Logger logger = LoggerFactory.getLogger(BaseTaskTest.class);
	private ActivityTrackerEventConfig activityEventCfg = new ActivityTrackerEventConfig("miss-piggy", "london");
	@InjectMocks
	private BaseAbstractTaskConfig baseTaskCfg;// = new BaseTaskConfig(idCatalog);


	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
			"break  | start | To be implemented... "
          , "break  | stop  | To be implemented... "
          , "pause  | second parameter value | To be implemented... "
          , "coffee | second parameter value | To be implemented... "
	})
	public void create(String identifier, String command, String expException) {
		BaseTask task = new BaseTask(activityEventCfg, baseTaskCfg);
		try {
			task.execute(identifier, command, null);
			fail("Expecting an " + AppException.class.getSimpleName() + " will be thrown.");
		}
		catch (TaskHandlerException te) {
			assertEquals(expException, te.getMessage());
		}
		catch (RuntimeException re) {
			if (re.getMessage().startsWith("java.lang.RuntimeException: To be implemented...")) {
				logger.warn(re.getMessage());
			}
		}
	}

}

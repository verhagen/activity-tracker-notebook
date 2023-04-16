package nl.verhagen.activitylogger.command.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nl.verhagen.activitylogger.command.AppException;
import nl.verhagen.activitylogger.command.IdentifierCatalog;
import nl.verhagen.activitylogger.command.IdentifierCatalogMock;
import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;

public class BaseTaskTest {
	private IdentifierCatalog idReg = new IdentifierCatalogMock();
	private Logger logger = LoggerFactory.getLogger(BaseTaskTest.class);
	private ActivityTrackerEventConfiguration activityEventCfg = new ActivityTrackerEventConfiguration("miss-piggy");
	private BaseTaskConfiguration baseTaskCfg = new BaseTaskConfiguration(idReg);


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

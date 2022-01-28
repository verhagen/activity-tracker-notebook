package nl.verhagen.activitylogger.command.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.activitylogger.command.AppException;
import nl.verhagen.activitylogger.command.IdentifierRegistery;
import nl.verhagen.activitylogger.command.IdentifierRegisteryMock;
import nl.verhagen.activitylogger.command.domain.ActivityTrackerEventConfiguration;

public class BaseTaskTest {
	private Logger logger = LoggerFactory.getLogger(BaseTaskTest.class);

	private IdentifierRegistery idReg = new IdentifierRegisteryMock();
	private ActivityTrackerEventConfiguration activityEventCfg = new ActivityTrackerEventConfiguration("miss-piggy");
	private BaseTaskConfiguration baseTaskCfg = new BaseTaskConfiguration(idReg);


	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
			"break  | start | qqq "
          , "break  | stop  | qqq "
          , "pause  | second parameter value | qqq "
          , "coffee | second parameter value | qqq "
	})
	public void create(String identifier, String command, String expException) {
		baseTaskCfg.getIdRegistery();
		baseTaskCfg.getTaskIdentifiers();
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

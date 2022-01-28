package nl.verhagen.activitylogger.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;


public class AppTestIncorrectCases {

	@Mock
	private AppRunner appRunner;

	@Mock
	private AppRunnerConfiguration appCfg;

//	@Captor
//	private ArgumentCaptor<String> identifierStr;
//
//	@Captor
//	private ArgumentCaptor<String> commandStr;
//
//	@Captor
//	private ArgumentCaptor<String> remainerStr;
	
	@InjectMocks
	private App app;

	private AutoCloseable closeable;

	@BeforeEach
	public void setUp() {
		closeable = openMocks(this);
	}
	@AfterEach
	public void tearDown() throws Exception {
		closeable.close();
	}

	@Disabled
	@ParameterizedTest
	@MethodSource("nl.verhagen.activitylogger.command.AppTestIncorrectCasesDataProvider#provideIncorrectCases")
	public void create(String arguments, String message, String clarification) {
		// Act
		try {
			app.execute(arguments.split(" "));
			fail("Expecting that an " + AppException.class.getSimpleName() + " will be thrown.");
		}
		catch (AppException ae) {
			assertEquals("help", ae.getMessage());
		}
	}

}

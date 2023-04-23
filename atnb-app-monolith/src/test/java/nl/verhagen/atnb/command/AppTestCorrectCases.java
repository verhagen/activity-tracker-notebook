package nl.verhagen.atnb.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AppTestCorrectCases {

	@Mock
	private AppRunner appRunner;

	@Mock
	private AppRunnerConfig appCfg;

	@Captor
	private ArgumentCaptor<String> identifierStr;

	@Captor
	private ArgumentCaptor<String> commandStr;

	@Captor
	private ArgumentCaptor<String> remainerStr;
	
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

	@ParameterizedTest
	@MethodSource("nl.verhagen.atnb.command.AppTestCorrectCasesDataProvider#provideCorrectCases")
	public void create(String arguments, String expIdentifier, String expCommand, String expRemainer) {
		// Act
		app.execute(arguments.split(" "));

		// Verify
		if (expRemainer == null) {
			verify(appRunner).execute(identifierStr.capture(), commandStr.capture());
		}
		else {
			verify(appRunner).execute(identifierStr.capture(), commandStr.capture(), remainerStr.capture());
		}

		// Assert
		assertEquals(expIdentifier, identifierStr.getValue());
		assertEquals(expCommand, commandStr.getValue());
		if (expRemainer != null) {
			assertEquals(expRemainer, remainerStr.getValue());
		}
	}

}

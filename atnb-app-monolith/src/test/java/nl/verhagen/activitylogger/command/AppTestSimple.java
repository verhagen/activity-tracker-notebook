package nl.verhagen.activitylogger.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AppTestSimple {

	@Mock
	private AppRunner appRunner;

	@Captor
	private ArgumentCaptor<String> identifierStr;

	@Captor
	private ArgumentCaptor<String> commandStr;
	
	@InjectMocks
	private App app;


	@Test
	public void executeIdentifier() {
		app.execute("article".split(" "));

		verify(appRunner).execute(identifierStr.capture(), commandStr.capture());
		
		assertEquals("article", identifierStr.getValue());
		assertEquals("start", commandStr.getValue());
	}
}

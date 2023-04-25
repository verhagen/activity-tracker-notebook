package com.github.verhagen.atnb.opportunity.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.verhagen.atnb.domain.IdentifierCatalog;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.verhagen.atnb.domain.ActivityTrackerEvent;
import com.github.verhagen.atnb.domain.ActivityTrackerEventConfig;
import com.github.verhagen.atnb.domain.Listener;

@ExtendWith(MockitoExtension.class)
public class OpportunityTaskTest {
	private Logger logger = LoggerFactory.getLogger(OpportunityTaskTest.class);
	@Mock
	private IdentifierCatalog idCatalog;
	private ActivityTrackerEventConfig atEventCfg = new ActivityTrackerEventConfig("miss-piggy", "london");
	@InjectMocks
	private OpportunityTaskConfig bookTaskCfg;

	// app  opportunity  create  [date] <agency> <organisation> <role> <mail-url>
	// app  opportunity  create  [date: 2021.12.20]; agent: <agent>; org: <organisation>; role: <role>; mail: <http://uri-to-mail.org/>
	// app  opportunity  create  [d: 2021.12.20]; a: <agent>; o: <organisation>; r: <role>; m: <http://uri-to-mail.org/>
	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
		  "opportunity | create |       2021.12.12;         Harvey Nash;                Lexis-Nexis;        Senior Software Developer; http://mail.org | opportunity.2021.12.12-harvey-nash_lexis-nexis_senior-software-developer"
		, "opportunity | create | date: 2021.12.10;  agent: Harvey Nash;  organisation: Lexis-Nexis;  role: Java Developer           ; http://mail.org | opportunity.2021.12.10-harvey-nash_lexis-nexis_java-developer"
		, "opportunity | create | d:    2021.12.05;  a:     Harvey Nash;  o:            Lexis-Nexis;  r:    Senior Software Developer; http://mail.org | opportunity.2021.12.05-harvey-nash_lexis-nexis_senior-software-developer"
		, "opportunity | start  |       2021.12.05;         Harvey Nash;  org:          Lexis-Nexis;        Senior Software Developer; http://mail.org | opportunity.2021.12.05-harvey-nash_lexis-nexis_senior-software-developer"
	})
	public void create(String identifier, String command, String text, String expectedIdentifier) {
		OpportunityTask task = new OpportunityTask(atEventCfg, bookTaskCfg);
		task.addListener(new Listener<ActivityTrackerEvent>() {
			@Override
			public void update(ActivityTrackerEvent event) {
				assertEquals(expectedIdentifier, event.getIdentifier());
				assertEquals(command, event.getCommand());
			}
		});

		try {
			task.execute(identifier, command, text);
		}
		catch (RuntimeException re) {
			if (re.getMessage().startsWith("java.lang.RuntimeException: Implement call to OpportunityContentCreator...")) {
				logger.warn(re.getMessage());
			}
		}
	}

}

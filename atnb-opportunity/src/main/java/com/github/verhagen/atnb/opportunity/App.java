package com.github.verhagen.atnb.opportunity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.verhagen.atnb.io.MediaTypes;
import com.github.verhagen.atnb.opportunity.PathFactoryConfig.IdentityOn;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	private Path dataPath = Paths.get("build/generated-activity-logbook");
	private PathFactoryConfig cfg = new PathFactoryConfig(dataPath.resolve("opportunity"), IdentityOn.DIRECTORY, "content");
	private PathFactory pathFactory = new PathFactory(cfg);
	private OpportunityDocumentFactory documentFactory = new OpportunityDocumentFactory();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

	public static void main(String[] args) {
		try {
			new App().execute(args);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void execute(String[] args) throws IOException {
		LocalDate creationDate = null;
		int creationDateIndex = -1;
		for (int index = 0; index < args.length; index++) {
			String arg = args[index];
			if (creationDate == null) {
				creationDate = extractLocalDate(arg);
				if (creationDate != null) {
					creationDateIndex = index;					
				}
			}
		}
		if (creationDate == null) {
			creationDate = LocalDate.now();
		}

		String agent = "";
		String organisation = "";
		String title = "";
		if (creationDateIndex == -1) {
			agent = args[0];
			organisation = args[1];
			title = args[2];
		}
		else {
			switch (creationDateIndex) {
			case 0:
				agent = args[1];
				organisation = args[2];
				title = args[3];
				break;
			case 1:
				agent = args[0];
				organisation = args[2];
				title = args[3];
				break;
			case 2:
				agent = args[0];
				organisation = args[1];
				title = args[3];
				break;
			case 3:
				agent = args[0];
				organisation = args[1];
				title = args[2];
				break;
			}
		}
		execute(creationDate, agent, organisation, title);
	}

	private LocalDate extractLocalDate(String arg) {
		try {
			return LocalDate.parse(arg, formatter);
		}
		catch (DateTimeParseException dtpe) {
			return null;
		}
	}

	public void execute(LocalDate creationDate, String agent, String organisation, String title) throws IOException {
		Opportunity opp = new Opportunity.Builder()
				.addCreationDate(creationDate)
				.addAgent(agent)
				.addOrganisation(organisation)
				.addTitle(title)
				.create();
		Path filePath = pathFactory.create(MediaTypes.TEXT_MARKDOWN, documentFactory.createIdentification(opp));
		
		logger.info("Creating file '" + filePath.toAbsolutePath() + "'");
		try(BufferedWriter writer = Files.newBufferedWriter(filePath, Charset.forName("UTF-8"))){
			writer.write(documentFactory.create(MediaTypes.TEXT_MARKDOWN, opp));
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

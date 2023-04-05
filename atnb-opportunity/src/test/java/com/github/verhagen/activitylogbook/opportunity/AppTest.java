package com.github.verhagen.activitylogbook.opportunity;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest {
	private static Path dataPath = Paths.get("build/generated-activity-logbook");

	@BeforeAll
	public static void setUp() throws IOException {
		FileUtils.deleteDirectory(dataPath.toFile());
	}

	@Test
	void createOpportunity() throws IOException {
		App app = new App();
//		app.execute(new String[] { "2021.12.20", "Agent", "Waldisney", "Mickey Mouse Standin" } );
//		app.execute(new String[] { "2021.12.22", "xquisit", "unknown", "Senior Java Developer" } );
//		app.execute(new String[] { "Computer Futures", "Rijkswaterstaat", "Java Developer" } );
//		app.execute(new String[] { "Technical Experts", "Sollution", "Interim Full Stack Developer" } );
//		app.execute(new String[] { "2022.01.05", "Computer Futures", "OCLC", "Senior Software Engineer" } );
//		app.execute(new String[] { "2022.01.07", "Source Labs", "BOL", "Senior Software Engineer" } );
//		app.execute(new String[] { "2022.01.07", "TEK Systems", "Sky OTT Global Video Platforms", "Lead Software Engineer Micro Seervices" } );
		app.execute(new String[] { "2022.01.06", "Enzo Tech", "Tennet", "Java Developer" } );
		app.execute(new String[] { "Hasvey Nash", "Tennet", "Java Developer" } );
	}
	
	// /Users/tjeerdverhagen/git/github/activity-logbook/activity-logbook-opportunity/build/generated-activity-logbook/opportunity/
}

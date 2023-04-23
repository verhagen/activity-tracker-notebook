package com.github.verhagen.atnb.opportunity;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.verhagen.atnb.io.MediaTypes;
import com.github.verhagen.atnb.opportunity.PathFactoryConfig.IdentityOn;

public class PathFactoryTest {
	private static Path dataPath = Paths.get("build/generated-logbook");

	@BeforeAll
	public static void setUp() throws IOException {
		FileUtils.deleteDirectory(dataPath.toFile());
//		try (Stream<Path> walk = Files.walk(dataPath)) {
//		    walk.sorted(Comparator.reverseOrder())
////		        .map(Path::toFile)
////		        .peek(System.out::println)
//		        .forEach(Files::deleteIfExists());
//		}
	}

	@Test
	public void create() throws IOException {
		PathFactoryConfig cfg = new PathFactoryConfig(dataPath, IdentityOn.FILE, "activity-logbook");
		PathFactory pathFactory = new PathFactory(cfg);
		Path filePath = pathFactory.create(MediaTypes.TEXT_MARKDOWN, "2012.12-logbook");

		assertTrue(cfg.getDataPath().toFile().exists());
		assertTrue(filePath.toFile().exists());
		assertEquals("2012.12-logbook.md", filePath.getFileName().toString());
		assertEquals("activity-logbook", filePath.getParent().getFileName().toString());
	}
}

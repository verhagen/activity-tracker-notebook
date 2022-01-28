package com.github.verhagen.activitylogbook.opportunity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.github.verhagen.activitylogbook.io.FileExtentionAndMediaType;
import com.github.verhagen.activitylogbook.io.MediaType;

public class PathFactory {
	private FileExtentionAndMediaType fileExtentionAndMediaType = new FileExtentionAndMediaType();
	private PathFactoryConfiguration cfg;


	public PathFactory(PathFactoryConfiguration cfg) {
		this.cfg = cfg;
	}


	public Path create(MediaType mediType, String identity) throws IOException {
		if (! cfg.getDataPath().toFile().exists()) {
			Files.createDirectories(cfg.getDataPath());
		}
		Files.createDirectories(cfg.getDocumentDirectory(identity));
		return Files.createFile(cfg.getDocumentFile(identity, fileExtentionAndMediaType.getFileExtension(mediType)));
	}
}

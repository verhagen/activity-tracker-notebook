package com.github.verhagen.atnb.opportunity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.github.verhagen.atnb.io.FileExtensionAndMediaType;
import com.github.verhagen.atnb.io.MediaType;

public class PathFactory {
	private FileExtensionAndMediaType fileExtensionAndMediaType = new FileExtensionAndMediaType();
	private PathFactoryConfig cfg;


	public PathFactory(PathFactoryConfig cfg) {
		this.cfg = cfg;
	}


	public Path create(MediaType mediType, String identity) throws IOException {
		if (! cfg.getDataPath().toFile().exists()) {
			Files.createDirectories(cfg.getDataPath());
		}
		Files.createDirectories(cfg.getDocumentDirectory(identity));
		return Files.createFile(cfg.getDocumentFile(identity, fileExtensionAndMediaType.getFileExtension(mediType)));
	}
}

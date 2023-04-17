package com.github.verhagen.atnb.opportunity;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathFactoryConfiguration {
	public enum IdentityOn {
		DIRECTORY
		, FILE
	}
	private Path dataPath;
	private IdentityOn identityOn;
	private String title;

	
	public PathFactoryConfiguration(Path dataPath, IdentityOn identityOn, String title) {
		this.dataPath = dataPath;
		this.identityOn = identityOn;
		this.title = title;
	}


	public Path getDataPath() {
		return dataPath;
	}

	public Path getDocumentDirectory(String identity) {
		return dataPath.resolve(Paths.get(IdentityOn.DIRECTORY == identityOn ? identity : title));
	}
	
	public Path getDocumentFile(String identity, String fileExtension) {
		return getDocumentDirectory(identity).resolve(Paths.get((IdentityOn.FILE == identityOn ? identity : title) + '.' + fileExtension));
	}
	
}

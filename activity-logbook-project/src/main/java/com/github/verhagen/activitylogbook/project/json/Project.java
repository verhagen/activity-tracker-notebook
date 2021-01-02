package com.github.verhagen.activitylogbook.project.json;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.verhagen.activitylogbook.domain.ActivityIdentifier;

public class Project implements ActivityIdentifier {
	@JsonProperty("repository-url")
	public URI repositoryUrl;
	public List<String> tags;
	public String description;

	@Override
	public String getIdentifier() {
		String path = repositoryUrl.getPath();
		if (repositoryUrl.getPath().endsWith("/")) {
			path = repositoryUrl.getPath().substring(0, path.lastIndexOf("/"));
		}

		if (path.lastIndexOf("/") > -1) {
			return path.substring(path.lastIndexOf("/") +1);
		}
		return path;
	} 

}

package com.github.verhagen.activitylogbook.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileExtensionAndMediaType {
	// The default file extension is always the first entry in the list
	private static final Map<MediaType, List<String>> FILE_EXTENSIONS_PER_MEDIA_TYPE = new HashMap<>();

	static {
		FILE_EXTENSIONS_PER_MEDIA_TYPE.put(MediaTypes.TEXT_MARKDOWN, Arrays.asList("md", "markdown"));
		FILE_EXTENSIONS_PER_MEDIA_TYPE.put(MediaTypes.TEXT_ASCIIDOC, Arrays.asList("adoc", "asciidoc"));
	}
	

	public String getFileExtension(MediaType mediaType) {
		if (! FILE_EXTENSIONS_PER_MEDIA_TYPE.containsKey(mediaType)) {
			throw new RuntimeException("No file extensions know for mediaType '" + mediaType + "'");
		}
		return FILE_EXTENSIONS_PER_MEDIA_TYPE.get(mediaType).get(0);
	}


	public MediaType getMediaType(String fileExtension) {
		for (Entry<MediaType, List<String>> entry : FILE_EXTENSIONS_PER_MEDIA_TYPE.entrySet()) {
			if (entry.getValue().contains(fileExtension.toLowerCase())) {
				return entry.getKey();
			}
		}
		throw new RuntimeException("No MediaType known for fileExtension '" + fileExtension + "'.");
	}
}

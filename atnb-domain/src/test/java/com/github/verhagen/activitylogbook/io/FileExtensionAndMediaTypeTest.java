package com.github.verhagen.activitylogbook.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FileExtensionAndMediaTypeTest {

	@Test
	void getFileExtension() {
		FileExtensionAndMediaType fileExtAndMediaType = new FileExtensionAndMediaType();
		
		String fileExtension = fileExtAndMediaType.getFileExtension(MediaTypes.TEXT_MARKDOWN);
		
		assertEquals("md", fileExtension);
	}

	@Test
	void getMediaType() {
		FileExtensionAndMediaType fileExtAndMediaType = new FileExtensionAndMediaType();
		
		MediaType mediaType = fileExtAndMediaType.getMediaType("md");
		
		assertEquals(MediaTypes.TEXT_MARKDOWN, mediaType);
	}

	@Test
	void getMediaType2() {
		FileExtensionAndMediaType fileExtAndMediaType = new FileExtensionAndMediaType();
		
		MediaType mediaType = fileExtAndMediaType.getMediaType("MARKDOWN");
		
		assertEquals(MediaTypes.TEXT_MARKDOWN, mediaType);
	}

}

package com.github.verhagen.atnb.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FileExtentionAndMediaTypeTest {

	@Test
	void getFileExtension() {
		FileExtentionAndMediaType fileExtAndMediaType = new FileExtentionAndMediaType();
		
		String fileExtension = fileExtAndMediaType.getFileExtension(MediaTypes.TEXT_MARKDOWN);
		
		assertEquals("md", fileExtension);
	}

	@Test
	void getMediaType() {
		FileExtentionAndMediaType fileExtAndMediaType = new FileExtentionAndMediaType();
		
		MediaType mediaType = fileExtAndMediaType.getMediaType("md");
		
		assertEquals(MediaTypes.TEXT_MARKDOWN, mediaType);
	}

	@Test
	void getMediaType2() {
		FileExtentionAndMediaType fileExtAndMediaType = new FileExtentionAndMediaType();
		
		MediaType mediaType = fileExtAndMediaType.getMediaType("MARKDOWN");
		
		assertEquals(MediaTypes.TEXT_MARKDOWN, mediaType);
	}

}

package com.github.verhagen.atnb.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.verhagen.atnb.core.domain.TextWithReferences;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TextWithReferencesTest {

	@ParameterizedTest
	@CsvSource(delimiter = '|', value = {
		"Follow the new on http://www.cnn.com to stay up to date.  | title: Follow the new on uri: ${uri-0} to stay up to date. | 1"
		, "Download ftp://download.com to https://cnn.com/lastest. | title: Download uri: ${uri-0} to ${uri-1}                  | 2"
		, "See link:for-downloading:ftp://download.com/ for more.. | title: See uri: link:${uri-0} for more..                   | 1"
	})
	public void extractAndReplaceUriByReferenceKeys(String text, String expectedText, int expectedUris) throws Exception {
		TextWithReferences textWithReferences = TextWithReferences.create(text);
//		assertEquals(text, textWithReferences.getText());
		assertEquals(expectedText, textWithReferences.getTextWithReferences());
		assertEquals(expectedUris, textWithReferences.getUriByKeys().size());
	}

}

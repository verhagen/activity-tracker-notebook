package com.github.verhagen.atnb.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class ActivityLogbookEntryTest {
	private Logger logger = LoggerFactory.getLogger(ActivityLogbookEntryTest.class);
    private Path generatdHtmlDir = Paths.get("build", "generated", "html");

	@Test
	void testName() {
		String markdownStr = asStringReader(create());

		MutableDataSet options = new MutableDataSet();

        // uncomment to set optional extensions
        //options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // You can re-use parser and renderer instances
        Node document = parser.parse(markdownStr);
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
//        System.out.println(html);
        generatdHtmlDir.toFile().mkdirs();
        File file = generatdHtmlDir.resolve("sample.html").toFile();
        if (file.exists()) {
        	file.delete();
        }
        try (FileWriter writer = new FileWriter(file)) {
        	writer.write(html);
        }
        catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Unabl to write html to file '" + file + "'", e);
		}
	}


	static List<String> create() {
		List<String> text = new ArrayList<>();
		text.add("## 2021.01.03 Sunday");
		text.add("- [03:32-03:59 https://livebook.manning.com/book/testing-angular-applications/chapter-2]");
		text.add("- [02:51-03:02 https://livebook.manning.com/book/testing-angular-applications/appendix-a]");
		text.add("- [01:57-02:34 https://livebook.manning.com/book/testing-angular-applications/chapter-1]");
//		text.add("");
//		text.add("");
		return text;
	}

	static String asStringReader(List<String> content) {
		StringBuilder bldr = new StringBuilder();
		for (String line : content) {
			if (bldr.length() > 0) {
				bldr.append("\n");
			}
			bldr.append(line);
		}
		return bldr.toString();
	}

}

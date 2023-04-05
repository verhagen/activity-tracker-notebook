package com.github.verhagen.activitylogbook.opportunity;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.github.verhagen.activitylogbook.io.MediaType;
import com.github.verhagen.activitylogbook.io.MediaTypes;

public class OpportunityDocumentFactory {
	private static final String SEP = "-";
	private static final String SEP_PART = "_";
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	private DateTimeFormatter formatterWithDay = DateTimeFormatter.ofPattern("yyyy.MM.dd EEEE");

	public SortedSet<MediaType> supportedMediaTypes() {
		return new TreeSet<>(Arrays.asList(MediaTypes.TEXT_MARKDOWN));
	}

 	public String create(MediaType mediaType, Opportunity opp) {
 		if (MediaTypes.TEXT_MARKDOWN != mediaType) {
 			throw DocumentFactoryException.createMediaTypeNotSupported(mediaType, OpportunityDocumentFactory.class);
 		}

		List<String> content = new ArrayList<>();
		content.add("# " + opp.getAgent() + " - " + opp.getOrganisation() + " - " + opp.getTitle());
		content.add("");
		content.add("Location: ");
		content.add("Status:");
		content.add("");
		content.add("## Actions");
		content.add("");
		content.add("## Contacts");
		content.add("");
		content.add("- @@@ (" + opp.getAgent()+ ")");
		content.add("");
		content.add("");
		content.add("## Details");
		content.add("");
		content.add("- ");
		content.add("- ");
		content.add("- ");
		content.add("");
		content.add("## Logbook");
		content.add("");
		content.add("### " + formatterWithDay.format(opp.getCreationDate()));
		content.add("");
		content.add("- Phone ");
		content.add("- Mail [" + opp.getTitle() + "](" + (opp.getMailUri() != null ? opp.getMailUri() : "") + ")");
		content.add("");

		StringBuilder bldr = new StringBuilder();
		for (int index = 0; index < content.size(); index++) {
			if (bldr.length() > 0) {
				bldr.append("\n");
			}
			bldr.append(content.get(index));
			
		}
		return bldr.toString();
	}

	public String createIdentification(Opportunity opp) {
		return (formatter.format(opp.getCreationDate()) + SEP + opp.getAgent() + SEP_PART + opp.getOrganisation() + SEP_PART + opp.getTitle())
				.toLowerCase().replaceAll(" ", SEP);
	}

}

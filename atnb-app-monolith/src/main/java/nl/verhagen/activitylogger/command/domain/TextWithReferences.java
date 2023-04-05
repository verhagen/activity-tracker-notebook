package nl.verhagen.activitylogger.command.domain;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.verhagen.activitylogger.command.InformationExtractor;

/**
 * Extract and replace URI with references, each uri like {@code https://www.cnn.com} is replaced by a reference
 * in the form {@code uri-#} Where the {@code #} is a number.
 * Inside the text with references, the references are shown as <code>${uri-#}</code>
 */
public class TextWithReferences {
	private final String text;
	private final String textWithReferences;
	private final Map<String, String> uriByKey;

	public TextWithReferences(String text) {
		this(text, text, null);
	}
	public TextWithReferences(String text, String textWithReferences, Map<String, String> uriByKey) {
		this.text = text;
		this.textWithReferences = textWithReferences;
		this.uriByKey = uriByKey;
	}
	public String getTextWithReferences() {
		return textWithReferences;
	}
	public Map<String, String> getUriByKeys() {
		return uriByKey;
	}
	public String getText() {
		return text;
	}
	public String lookup(String text) {
		Matcher matcher = Pattern.compile("\\$\\{(uri-\\d)\\}").matcher(text);
		if (matcher.matches() && true) {
			return uriByKey.get(matcher.group(1));
		}
		return text;
	}
	public boolean containsReferences( ) {
		return uriByKey != null && uriByKey.size() > 0;
	}

	// Replace every uri in the text, by a reference in the form of '${uri-#}'.
	public static TextWithReferences create(String text) {
		return create(text, null);
	}
	public static TextWithReferences create(String text, List<InformationExtractor<URI>> infoExtractors) {
		if (! text.contains("://")) {
			return new TextWithReferences(text);
		}
		boolean isValueSeparatorAvailable = text.contains(";");

		Pattern markDownPattern = Pattern.compile("\\[(.+)\\]\\((.*)\\)");
		Pattern asciiDocPattern = Pattern.compile("(.+)\\[(.*)\\]");

		if (markDownPattern.matcher(text).matches()) {
			Matcher matcher = markDownPattern.matcher(text);
			System.out.println("markDown");
			if (matcher.matches()) {
				String title = matcher.group(1);
				String uri = matcher.group(2);
				//textParts.set(index, "[" + title + "]" + "(${uri-" + uriIndex + "})");
				text = "title: " + title + (isValueSeparatorAvailable ? ";" : "") + " uri: " + uri;
			}
		}
		else if (asciiDocPattern.matcher(text).matches()) {
			Matcher matcher = asciiDocPattern.matcher(text);
			System.out.println("asciiDocPattern");
			if (matcher.matches()) {
				String title = matcher.group(2);
				String uri = matcher.group(1);
				//textParts.set(index, "${uri-" + uriIndex + "}" + "[" + title + "]");
				text = "title: " + title + (isValueSeparatorAvailable ? ";" : "") + " uri: " + uri;
			}
		}
		else if (! isValueSeparatorAvailable && text.contains("://")) {
			int indexOfCoSlSl = text.indexOf("://");
			int indexOfSpace = text.substring(0, indexOfCoSlSl).lastIndexOf(" ");
			if (indexOfSpace > 0) {
				String title = text.substring(0, indexOfSpace);
				String uri = text.substring(indexOfSpace +1);
				text = "title: " + title + (isValueSeparatorAvailable ? ";" : "") + " uri: " + uri;
			}
			else if (infoExtractors != null && infoExtractors.size() > 0) {
				try {
					URI uri = URI.create(text);
					Optional<InformationExtractor<URI>> optional = infoExtractors.stream().filter(e -> e.isInfoExtractionPossible(uri)).findFirst();
					if (optional.isPresent()) {
						text = "title: " + optional.get().getTitle(uri) + (isValueSeparatorAvailable ? ";" : "") + " uri: " + uri;
					}
				}
				catch (IllegalArgumentException iae) {
					// Seemingly the text is not an uri.
				}
			}
		}
		
		List<String> textParts = Stream.of(text.split(" ")).collect(Collectors.toList())  ;
		Map<String, String> uriByKey = new HashMap<>();
		int uriIndex = 0;
		for (int index = 0; index < textParts.size(); index++) {
			String textPart = textParts.get(index);
			if (textPart.contains("://")) {
				int indexOfDss = textPart.indexOf("://");
				// Is the uri prefixed with a field name?
				if (textPart.substring(0, indexOfDss).contains(":")) {
					String possibleFieldName = textPart.substring(0, textPart.indexOf(":") +1); // Just the possible field with the colon ':'
					String possibleValue = textPart.substring(textPart.indexOf(":") +1);  // The the part after the colon, which also contains the '://'
					textParts.set(index, possibleFieldName + "${uri-" + uriIndex + "}");
					uriByKey.put("uri-" + uriIndex, possibleValue);
				}
				//textPart.substring(0, indexOfDss)
//				else if (markDownPattern.matcher(textPart).matches()) {
//					Matcher matcher = markDownPattern.matcher(textPart);
//					System.out.println("text");
//					if (matcher.matches()) {
//						String title = matcher.group(1);
//						String uri = matcher.group(2);
//						//textParts.set(index, "[" + title + "]" + "(${uri-" + uriIndex + "})");
//						textParts.set(index, "title: " + title + (isValueSeparatorAvailable ? ";" : "") + " uri: " + "${uri-" + uriIndex + "}");
//						uriByKey.put("uri-" + uriIndex, uri);
//					}
//				}
//				else if (asciiDocPattern.matcher(textPart).matches()) {
//					Matcher matcher = asciiDocPattern.matcher(textPart);
//					System.out.println("asciiDocPattern");
//					if (matcher.matches()) {
//						String title = matcher.group(2);
//						String uri = matcher.group(1);
//						//textParts.set(index, "${uri-" + uriIndex + "}" + "[" + title + "]");
//						textParts.set(index, "title: " + title + (isValueSeparatorAvailable ? ";" : "") + " uri: " + "${uri-" + uriIndex + "}");
//						uriByKey.put("uri-" + uriIndex, uri);
//					}
//				}
				else {
					textParts.set(index, "${uri-" + uriIndex + "}");
					uriByKey.put("uri-" + uriIndex, textPart);
				}
				uriIndex++;
			}
		}
		
		return new TextWithReferences(text, textParts.stream().collect(Collectors.joining(" ")), uriByKey);
	}
	
}

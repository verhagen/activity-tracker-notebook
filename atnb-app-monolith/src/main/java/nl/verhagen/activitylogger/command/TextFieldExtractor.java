package nl.verhagen.activitylogger.command;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.verhagen.activitylogger.command.domain.TextWithReferences;

public class TextFieldExtractor {
	private Logger logger = LoggerFactory.getLogger(TextFieldExtractor.class);
	private final List<TextField> textFields;
	private final Map<String, TextField> textFieldsMap;
//	private final List<IdentifierConverter<?>> idConverters;
	private final List<InformationExtractor<URI>> infoExtractors;


	public TextFieldExtractor(List<TextField> textFields) {
		this(textFields, null);
	}
	public TextFieldExtractor(List<TextField> textFields, List<InformationExtractor<URI>> infoExtractors) {
		this.textFields = textFields;
		this.textFieldsMap = textFields.stream().collect(Collectors.toMap(TextField::getKey, Function.identity()));
//		this.idConverters = idConverters;
		this.infoExtractors = infoExtractors;
	}

	public Map<String, Object> extractAsObjects(String text) {
		TextWithReferences textWithReferences = TextWithReferences.create(text, infoExtractors);
		String textWithRef = textWithReferences.getTextWithReferences();
		boolean isValueSeparatorAvailable = textWithRef.contains(";");
		boolean isKeyValueSeparatorAvailable = textWithRef.contains(":");

		if (textWithReferences.containsReferences()) {
			// Check if the links are represented as MarkDown or AsciiDoc links with title.
			// '[The title for MarkDown](${uri-#})'
			// '${uri-#}[The title for AsciiDoc]'
//			Pattern markDownPattern = Pattern.compile("\\[(.+)\\]\\(\\$\\{uri-\\d\\}\\)");
			Pattern markDownPattern = Pattern.compile("\\[(.+)\\]\\((.+)\\)");
			Matcher matcher = markDownPattern.matcher(textWithRef);
			if (matcher.matches()) {
				logger.info("Found markDownPattern link pattern...");
			}
			Pattern asciiDocPattern = Pattern.compile("(.+)\\[(.+)\\]");
			matcher = asciiDocPattern.matcher(textWithRef);
			if (matcher.matches()) {
				logger.info("Found asciiDocPattern link pattern...");
			}
		}

		List<String> textParts;
		if (isValueSeparatorAvailable) {
			textParts = Stream.of(textWithRef.split(";"))
					.map(t -> t.trim())
					.collect(Collectors.toList());
		}
		else if (isKeyValueSeparatorAvailable) {
			textParts = new LinkedList<>();
			String[] tmpParts = textWithRef.split(":");
			String previousPossibleKey = null;
			for (int index = 0; index < tmpParts.length; index++) {
				String tmpPart = tmpParts[index].trim();
				String nextPossibleKey = null;
				String remainer = null;
				if (tmpPart.contains(" ")) {
					nextPossibleKey = tmpPart.substring(tmpPart.lastIndexOf(" ") +1);
					remainer = tmpPart.substring(0, tmpPart.lastIndexOf(" ")).trim();
				}
				else if (index == 0) {
					nextPossibleKey = tmpPart;
				}
				else {
					remainer = tmpPart;
				}
				
				if (previousPossibleKey != null && remainer != null) {
					if (textFieldsMap.containsKey(previousPossibleKey)) {
						textParts.add(previousPossibleKey + ":" + remainer);
					}
				}
				previousPossibleKey = nextPossibleKey;
			}
		}
		else {
			return null;
		}

		Map<String, Object> values = new HashMap<>();
		for (int index = 0; index < textFields.size(); index++) {
			TextField textField = textFields.get(index);
			String textPart = (index < textParts.size()) ? textParts.get(index) : "";
			if (textPart.contains(":") && ! textPart.substring(textPart.indexOf(":"), textPart.indexOf(":") +3).equals("://")) {
				List<String> keyValueParts = new ArrayList<>();
				keyValueParts.add(textPart.substring(0, textPart.indexOf(":")).trim());
				keyValueParts.add(textPart.substring(textPart.indexOf(":") +1, textPart.length()).trim());
				
				if (keyValueParts.size() == 2 && textField.isKeyMatch(keyValueParts.get(0))) {
					Object obj = textField.parse(textWithReferences.lookup(keyValueParts.get(1)));
					if (obj != null) {
						values.put(textField.getKey(), obj);
					}
				}
				else {
					throw new RuntimeException("Needs some implementation / error checking");
				}
			}
			else {
				Object obj = textField.parse((index < textParts.size()) ? textWithReferences.lookup(textParts.get(index)) : "");
				if (obj != null) {
					values.put(textField.getKey(), obj);
				}
			}
		}
		return values;
	}

	public Map<String, String> convertToStrings(Map<String, Object> values) {
		return values.entrySet().stream()
				.collect(Collectors.toMap(
						e -> e.getKey(), 
						e -> textFieldsMap.get(e.getKey()).format(e.getValue())));
	}

	public Map<String, String> extract(String text) {
		return convertToStrings(extractAsObjects(text));
	}

	public TextField getTextField(String key) {
		return textFieldsMap.get(key);
	}
	public Map<String, TextField> getTextFields() {
		return Collections.unmodifiableMap(textFieldsMap);
	}

}

package nl.verhagen.atnb.command;

import org.apache.commons.lang3.StringUtils;

public class StringTextField extends AbstractTextField {

	public StringTextField(String key) {
		this(key, true);
	}
	public StringTextField(String key, boolean isRequired) {
		super(String.class, key, isRequired);
	}


	@Override
	public String format(Object value) {
		return (String)value;
	}

	@Override
	public String parse(String text) {
		if (StringUtils.trimToNull(text) == null){
			return null;
		}

		return text;
	}
	
}

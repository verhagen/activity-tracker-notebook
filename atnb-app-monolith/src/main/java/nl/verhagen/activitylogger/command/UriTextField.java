package nl.verhagen.activitylogger.command;

import java.net.URI;

import org.apache.commons.lang3.StringUtils;

public class UriTextField extends AbstractTextField {

	public UriTextField(String key) {
		this(key, true);
	}
	public UriTextField(String key, boolean isRequired) {
		super(URI.class, key, isRequired);
	}

	
	@Override
	public String format(Object value) {
		return ((URI)value).toString();
	}

	@Override
	public Object parse(String text) {
		if (StringUtils.trimToNull(text) == null){
			return null;
		}

		return URI.create(text);
	}

}

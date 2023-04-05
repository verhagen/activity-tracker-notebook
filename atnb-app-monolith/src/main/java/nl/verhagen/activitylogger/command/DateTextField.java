package nl.verhagen.activitylogger.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public class DateTextField extends AbstractTextField {
	private final DateTimeFormatter formatter;


	public DateTextField(String key, DateTimeFormatter formatter) {
		this(key, formatter, true);
	}
	public DateTextField(String key, DateTimeFormatter formatter, boolean isRequired) {
		super(LocalDate.class, key, isRequired);
		this.formatter = formatter;
	}


	public DateTimeFormatter getFormatter() {
		return formatter;
	}


	@Override
	public String format(Object value) {
		return ((LocalDate)value).format(formatter);
	}

	@Override
	public LocalDate parse(String date) {
		if (StringUtils.trimToNull(date) == null){
			return null;
		}
		return LocalDate.parse(date, formatter);
	}

}

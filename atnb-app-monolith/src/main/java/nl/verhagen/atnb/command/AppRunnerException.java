package nl.verhagen.atnb.command;

import com.github.verhagen.atnb.core.AppException;

@SuppressWarnings("serial")
public class AppRunnerException extends AppException {

	public AppRunnerException(String message) {
		super(message);
	}

	public AppRunnerException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppRunnerException(Throwable cause) {
		super(cause);
	}

}

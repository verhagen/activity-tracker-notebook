package nl.verhagen.atnb.command;

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

package nl.verhagen.activitylogger.command.task;

@SuppressWarnings("serial")
public class TaskHandlerException extends RuntimeException {

	public TaskHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskHandlerException(String message) {
		super(message);
	}

	public TaskHandlerException(Throwable cause) {
		super(cause);
	}

}

package nl.verhagen.activitylogger.command;

import java.util.Arrays;
import java.util.stream.Collectors;

public class App {
	private final AppRunner appRunner;

    public  App() {
        this(new AppRunner(new AppRunnerConfiguration()));
    }
    public App(AppRunner appRunner) {
        if (appRunner == null) {
            throw new IllegalArgumentException("Argument 'appRunner' should not be null.");
        }
    	this.appRunner = appRunner;
    }

    public static void main(String[] args) {
    	new App().execute(args);
    }

    public void execute(String[] args) {
        String identifier;
        String command;
        String remainer;

        if (args.length == 1) {
            identifier = args[0];
            command = "start";
            appRunner.execute(identifier, command);
        }
        else if (args.length == 2) {
            identifier = args[0];
            isKnownIdentifier(identifier);
            if (appRunner.isKnownTaskCommand(identifier, args[1])) {  //commandNames.contains(args[1].toLowerCase())
            	command = args[1];
            	appRunner.execute(identifier, command);
            }
            else {
            	command = "start";
            	remainer = args[1];
            	appRunner.execute(identifier, command, remainer);
            }
        }
        else if (args.length >= 3) {
        	int startIndex = 0;
            identifier = args[0];
            if (appRunner.isKnownTaskCommand(identifier, args[1])) {
        		command = args[1];
        		startIndex = 2;
            }
            else {
            	command = "start";
            	startIndex = 1;
            }
            remainer = Arrays.stream(args, startIndex, args.length).collect(Collectors.joining(" "));

            appRunner.execute(identifier, command, remainer);
        }
        else {
            System.out.println("Usage " + App.class.getName() + ": <identifier> [command] [remainer... ]");
            System.out.println("Default command is start");
        }
    }

    private void isKnownIdentifier(String identifier) {
		appRunner.isKnownTaskIdentifier(identifier);
		
	}

}

package calculator.input.command;

public class HelpCommand implements ICommand {

    public static String getName() {
        return "help";
    }

    public CommandMarker getCommandMarker() {
        return CommandMarker.HELP_MARKER;
    }

}
package calculator.input;

public class HelpCommand implements ICommand {

    public CommandMarker getCommandMarker() {
        return CommandMarker.HELP_MARKER;
    }

}

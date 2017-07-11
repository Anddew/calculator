package calculator.input;

public class BreakCommand implements ICommand {

    public CommandMarker getCommandMarker() {
        return CommandMarker.QUIT_MARKER;
    }

}

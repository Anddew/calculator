package calculator.input;

public class QuitCommand implements ICommand {

    public CommandMarker getCommandMarker() {
        return CommandMarker.QUIT_MARKER;
    }

}

package calculator.results;

public class AppBreak implements ICommand {

    public CommandMarker getCommandMarker() {
        return CommandMarker.QUIT_MARKER;
    }

}

package main.calculator.command;

public class QuitCommand implements ICommand {

    @Override
    public CommandMarker getCommandMarker() {
        return CommandMarker.QUIT_MARKER;
    }

    public static String getName() {
        return "quit";
    }

}

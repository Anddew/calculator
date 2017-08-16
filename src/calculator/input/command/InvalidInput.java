package calculator.input.command;

public class InvalidInput implements ICommand {

    private String comment;

    public InvalidInput(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public CommandMarker getCommandMarker() {
        return CommandMarker.INVALID_INPUT_MARKER;
    }

}

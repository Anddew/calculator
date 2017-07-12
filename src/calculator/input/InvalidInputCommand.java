package calculator.input;

public class InvalidInputCommand implements ICommand {

    private String comment;

    public InvalidInputCommand(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public CommandMarker getCommandMarker() {
        return CommandMarker.ILLEGAL_INPUT_MARKER;
    }

}

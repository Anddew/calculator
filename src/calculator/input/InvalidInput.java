package calculator.input;

public class InvalidInput implements ICommand {

    private String comment;

    public InvalidInput(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public CommandMarker getCommandMarker() {
        return CommandMarker.ILLEGAL_INPUT_MARKER;
    }

}

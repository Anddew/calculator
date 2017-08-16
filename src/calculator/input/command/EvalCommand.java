package calculator.input.command;

import calculator.input.command.evalcommandtoken.IEvalCommandToken;

import java.util.List;

public class EvalCommand implements ICommand {

    private List<IEvalCommandToken> elementsList;

    public EvalCommand(List<IEvalCommandToken> elementsList) {
        this.elementsList = elementsList;
    }

    public List<IEvalCommandToken> getElementsList() {
        return elementsList;
    }

    public CommandMarker getCommandMarker() {
        return CommandMarker.EVAL_MARKER;
    }

    public static String getName() {
        return "eval";
    }
}

package main.calculator.command;

import main.calculator.command.creator.evalcommandtoken.IEvalCommandToken;

import java.util.List;

public class EvalCommand implements ICommand {

    private List<IEvalCommandToken> tokenList;

    public EvalCommand(List<IEvalCommandToken> tokenList) {
        this.tokenList = tokenList;
    }

    public List<IEvalCommandToken> getTokenList() {
        return tokenList;
    }

    public CommandMarker getCommandMarker() {
        return CommandMarker.EVAL_MARKER;
    }

    public static String getName() {
        return "eval";
    }
}

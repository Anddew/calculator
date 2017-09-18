package main.calculator.command.creator.evalcommandtoken;

public class ErrorToken implements IEvalCommandToken {

    private String errorMessage;

    public ErrorToken(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public EvalCommandTokenType getTokenType() {
        return EvalCommandTokenType.ERROR;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

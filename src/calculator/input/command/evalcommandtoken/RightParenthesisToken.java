package calculator.input.command.evalcommandtoken;

public class RightParenthesisToken implements IEvalCommandToken {

    @Override
    public EvalCommandTokenType getTokenType() {
        return EvalCommandTokenType.OPERATION_END;
    }

}

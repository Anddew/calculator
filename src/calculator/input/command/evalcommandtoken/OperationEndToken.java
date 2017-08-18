package calculator.input.command.evalcommandtoken;

public class OperationEndToken implements IEvalCommandToken {

    @Override
    public EvalCommandTokenType getTokenType() {
        return EvalCommandTokenType.OPERATION_END;
    }

}

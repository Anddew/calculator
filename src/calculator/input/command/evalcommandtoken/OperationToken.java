package calculator.input.command.evalcommandtoken;

import calculator.operation.SequentialOperation;

public class OperationToken implements IEvalCommandToken {

    private SequentialOperation operation;

    public OperationToken(SequentialOperation operation) {
        this.operation = operation;
    }

    public SequentialOperation getOperation() {
        return operation;
    }

    @Override
    public EvalCommandTokenType getTokenType() {
        return EvalCommandTokenType.OPERATION;
    }
}

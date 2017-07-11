package calculator.input;

import calculator.operation.IOperation;

public class EvalCommand implements ICommand {

    private double[] argumentsArray;
    private IOperation operation;

    public EvalCommand(double[] argumentsArray, IOperation operation) {
        this.argumentsArray = argumentsArray;
        this.operation = operation;
    }

    public double[] getArgumentsArray() {
        return argumentsArray;
    }

    public IOperation getOperation() {
        return operation;
    }

    public CommandMarker getCommandMarker() {
        return CommandMarker.EVAL_MARKER;
    }

}

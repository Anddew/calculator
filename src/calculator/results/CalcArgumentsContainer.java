package calculator.results;

import calculator.operations.IOperation;

public class CalcArgumentsContainer implements ICommand {

    private double[] argumentsArray;
    private IOperation operation;

    public CalcArgumentsContainer(double[] argumentsArray, IOperation operation) {
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

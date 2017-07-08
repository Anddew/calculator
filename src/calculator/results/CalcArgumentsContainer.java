package calculator.results;

import calculator.operations.Operation;

public class CalcArgumentsContainer extends ResultOperation {

    private double[] argumentsArray;
    private Operation operation;
    private ResultMarker resultMarker;

    public CalcArgumentsContainer(double[] argumentsArray, Operation operation) {
        super(ResultMarker.ArgumentsContainer);
        this.argumentsArray = argumentsArray;
        this.operation = operation;
    }

    public double[] getArgumentsArray() {
        return argumentsArray;
    }

    public Operation getOperation() {
        return operation;
    }

}

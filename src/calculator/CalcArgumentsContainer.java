package calculator;

import calculator.operations.Operation;

public class CalcArgumentsContainer {

    private double[] argumentsArray;
    private Operation operation;

    public CalcArgumentsContainer(double[] argumentsArray, Operation operation) {
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

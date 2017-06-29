package calculator;

import calculator.operations.IOperation;

public class Argument {

    private double firstArgument;
    private double secondArgument;
    private IOperation operation;

    public Argument() {
        firstArgument = 0;
        secondArgument = 0;
        operation = null;
    }

    public Argument(double firstArgument, IOperation operation, double secondArgument) {
        this.firstArgument = firstArgument;
        this.operation = operation;
        this.secondArgument = secondArgument;
    }

    public double getFirstArgument() {
        return firstArgument;
    }

    public double getSecondArgument() {
        return secondArgument;
    }

    public IOperation getOperation() {
        return operation;
    }

    public void setFirstArgument(double firstArgument) {
        this.firstArgument = firstArgument;
    }

    public void setSecondArgument(double secondArgument) {
        this.secondArgument = secondArgument;
    }

    public void setOperation(IOperation operation) {
        this.operation = operation;
    }

    public void setArgument(double firstArgument, IOperation operation, double secondArgument) {
        this.firstArgument = firstArgument;
        this.operation = operation;
        this.secondArgument = secondArgument;
    }
}

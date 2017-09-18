package main.calculator.operation;

public class OperationResult {

    private boolean isResult;
    private double result;
    private String errorMessage;

    OperationResult(double result) {
        this.isResult = true;
        this.result = result;
    }

    OperationResult(String errorMessage) {
        this.isResult = false;
        this.errorMessage = errorMessage;
    }

    public boolean isResult() {
        return isResult;
    }

    public double getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

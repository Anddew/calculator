package calculator.operation;

import calculator.input.stringparsingtool.Bounds;

import java.util.List;

class Logarithm implements IOperation {

    private Bounds bounds = new Bounds(2, 2);

    @Override
    public String getName() {
        return "log";
    }

    @Override
    public Bounds getOperandsBounds() {
        return bounds;
    }

    @Override
    public double apply(List<Double> argumentsList) {
        if (argumentsList.size() != 2) {
            throw new IllegalArgumentException("Logarithm function must have only 2 arguments");
        } else {
            double base = argumentsList.get(0);
            double exp = argumentsList.get(1);
            return Math.log(exp) / Math.log(base);
        }

    }

}

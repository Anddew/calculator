package calculator.operation;

import calculator.input.stringparsingtool.Bounds;

import java.util.List;

class PowerOf implements IOperation {

    private Bounds bounds = new Bounds(2, 2);

    @Override
    public String getName() {
        return "pow";
    }

    @Override
    public Bounds getOperandsBounds() {
        return bounds;
    }

    @Override
    public double apply(List<Double> argumentsList) {
        if (argumentsList.size() != 2) {
            throw new IllegalArgumentException("PowerOf function must have only 2 arguments");
        } else {
            return Math.pow(argumentsList.get(0), argumentsList.get(1));
        }

    }

}

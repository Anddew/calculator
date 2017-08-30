package calculator.operation;

import calculator.input.stringparsingtool.Bounds;

import java.util.List;

abstract class SequentialOperation implements IOperation {

    private Bounds bounds = new Bounds(1, 20);

    @Override
    public double apply(List<Double> argumentsList) {
        double accumulator = argumentsList.get(0);
        for(Double elem: argumentsList.subList(1, argumentsList.size())) {
            accumulator = accumulate(accumulator, elem);
        }
        return accumulator;
    }

    @Override
    public Bounds getOperandsBounds() {
        return bounds;
    }

    public abstract double accumulate(double accumulator, double value);

}
package calculator.operation;

import calculator.input.stringparsingtool.Bounds;

import java.util.List;
import java.util.Optional;

abstract class SequentialOperation implements IOperation {

    private final static int MAX_OPERANDS_NUMBER = 20;

    private final static Bounds bounds = new Bounds(1, MAX_OPERANDS_NUMBER);

    @Override
    public OperationResult apply(List<Double> argumentsList) {
        Optional<String> errorMessage = checkArguments(argumentsList);
        if(!errorMessage.isPresent()) {
            double accumulator = argumentsList.get(0);
            for(Double elem: argumentsList.subList(1, argumentsList.size())) {
                accumulator = accumulate(accumulator, elem);
            }
            return new OperationResult(accumulator);
        } else {
            return new OperationResult(errorMessage.get());
        }
    }

    @Override
    public Bounds getOperandsBounds() {
        return bounds;
    }

    public abstract double accumulate(double accumulator, double value);

}
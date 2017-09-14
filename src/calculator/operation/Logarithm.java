package calculator.operation;

import calculator.input.stringparsingtool.Bounds;

import java.util.List;
import java.util.Optional;

class Logarithm implements IOperation {

    private static final Bounds bounds = new Bounds(2, 2);

    @Override
    public String getName() {
        return "log";
    }

    @Override
    public Bounds getOperandsBounds() {
        return bounds;
    }

    @Override
    public OperationResult apply(List<Double> argumentsList) {
        Optional<String> errorMessage = checkArguments(argumentsList);
        if(!errorMessage.isPresent()) {
            double base = argumentsList.get(0);
            double exp = argumentsList.get(1);
            return new OperationResult(Math.log(exp) / Math.log(base));
        } else {
            return new OperationResult(errorMessage.get());
        }
    }

    @Override
    public Optional<String> checkArguments(List<Double> argumentsList) {
        if(argumentsList.get(0) > 0 && argumentsList.get(1) > 0) {
            return Optional.empty();
        } else {
            return Optional.of("Calculating error. Both logarithm arguments must be positive.");
        }
    }

}

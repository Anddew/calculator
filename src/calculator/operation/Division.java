package calculator.operation;

import java.util.List;
import java.util.Optional;

class Division extends SequentialOperation {

    @Override
    public String getName() {
        return "div";
    }

    @Override
    public Optional<String> checkArguments(List<Double> argumentsList) {
        if(argumentsList.subList(1, argumentsList.size()).contains(0d)) {
            return Optional.of("Calculating error. Division by zero.");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public double accumulate(double accumulator, double value) {
        return accumulator / value;
    }

}
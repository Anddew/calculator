package calculator.operation;

import java.util.List;

class PowerOf extends SequentialOperation {

    @Override
    public String getName() {
        return "pow";
    }

    public double apply(List<Double> argumentsList) {
        if (argumentsList.size() != 2) {
            throw new IllegalArgumentException("PowerOf function must have only 2 arguments");
        }
        return accumulate(argumentsList.get(0), argumentsList.get(1));
    }

    @Override
    public double accumulate(double accumulator, double value) {
        return Math.pow(accumulator, value);
    }

}

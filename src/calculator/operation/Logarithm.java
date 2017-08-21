package calculator.operation;

import java.util.List;

class Logarithm implements IOperation {

    @Override
    public String getName() {
        return "log";
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

package calculator.operation;

import java.util.List;

public class Logarithm implements IOperation {

    public String getName() {
        return "log";
    }

    public double action(List<Double> argumentsList) {
        if (argumentsList.size() != 2) {
            throw new IllegalArgumentException("Logarithm function must have only 2 arguments");
        }
        double base = Math.log(argumentsList.get(0));
        double exponent = Math.log(argumentsList.get(1));
        return exponent / base;
    }

}

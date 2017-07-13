package calculator.operation;

import java.util.List;

public class Power implements IOperation {

    public String getName() {
        return "pow";
    }

    public double action(List<Double> argumentsList) {
        if (argumentsList.size() != 2) {
            throw new IllegalArgumentException("Power function must have only 2 arguments");
        }
        return Math.pow(argumentsList.get(0), argumentsList.get(1));
    }

}

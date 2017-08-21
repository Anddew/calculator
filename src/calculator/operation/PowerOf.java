package calculator.operation;

import java.util.List;

class PowerOf implements IOperation {

    @Override
    public String getName() {
        return "pow";
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

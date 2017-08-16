package calculator.operation;

import java.util.List;

public abstract class SequentialOperation implements IOperation {

    public double apply(List<Double> argumentsList) {
        if(argumentsList.isEmpty()) {
            return 0;
        } else {
            double accumulator = argumentsList.get(0);
            for(Double elem: argumentsList.subList(1, argumentsList.size())) {
                accumulator = accumulate(accumulator, elem);
            }
            return accumulator;
        }
    }

    public abstract double accumulate(double accumulator, double value);

}
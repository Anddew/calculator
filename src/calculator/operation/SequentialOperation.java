package calculator.operation;

import java.util.List;

public abstract class SequentialOperation implements IOperation {

    @Override
    public double apply(List<Double> argumentsList) {
        // TODO: 16.08.2017 количество параметров должно быть от 1 до 20 
        if(argumentsList.isEmpty()) {
            // TODO: 21.08.2017 переделать в InvalidInput предварительную валидацию
            throw new IllegalArgumentException("Arguments list is empty.");
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
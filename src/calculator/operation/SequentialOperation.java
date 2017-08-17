package calculator.operation;

import java.util.List;

public abstract class SequentialOperation implements IOperation {

    // TODO: 16.08.2017 Перенести метод apply в IOperation. Pow Log должен напрямую impl IOperation. Везде в проге работать с IOperation.
    public double apply(List<Double> argumentsList) {
        // TODO: 16.08.2017 количество параметров должно быть от 1 до 20 
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
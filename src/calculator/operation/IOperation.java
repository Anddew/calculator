package calculator.operation;

import java.util.List;

public interface IOperation {

    String getName();

    double apply(List<Double> argumentsList);

}
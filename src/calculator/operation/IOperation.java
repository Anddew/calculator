package calculator.operation;


import java.util.List;

public interface IOperation {

    double action(List<Double> argumentsList);

    String getName();

}
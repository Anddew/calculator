package calculator.operation;


import java.util.List;

public class Multiplication implements IOperation {

    public String getName() {
        return "mul";
    }

    public double action(List<Double> argumentsList) {

        double result = argumentsList.get(0);
        for (double elem: argumentsList.subList(1,argumentsList.size())) {
            result *= elem;
        }
        return result;
    }
}

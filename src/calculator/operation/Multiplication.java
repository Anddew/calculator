package calculator.operation;


import java.util.List;

public class Multiplication implements IOperation {

    public char getSign() {
        return '*';
    }

    public double action(List<Double> argumentsList) {

        double result = argumentsList.get(0);
        for (int i = 1; i < argumentsList.size(); i++) {
            result *= argumentsList.get(i);
        }
        return result;
    }
}

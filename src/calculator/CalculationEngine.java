package calculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import calculator.operations.*;

public class CalculationEngine {

    public List<IOperation> operations;

    public CalculationEngine() {
        operations = new ArrayList<>();
        operations.add(new Addition());
        operations.add(new Subtraction());
        operations.add(new Multiplication());
        operations.add(new Division());
    }

    public double calculate(double arg1, IOperation operation, double arg2) throws IOException {
        return operation.action(arg1, arg2);
    }

}

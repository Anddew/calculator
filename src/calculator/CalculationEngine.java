package calculator;

import calculator.operation.IOperation;
import calculator.input.EvalCommand;

import java.io.IOException;
import java.util.List;

public class CalculationEngine {

    public double calculate(EvalCommand argument) throws IOException {

        IOperation operation = argument.getOperation();
        List<Double> argumentsList = argument.getArgumentsList();
        return operation.action(argumentsList);

    }
}

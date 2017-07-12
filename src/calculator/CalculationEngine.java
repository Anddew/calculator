package calculator;

import calculator.operation.IOperation;
import calculator.input.EvalCommand;

import java.io.IOException;

public class CalculationEngine {

    public double calculate(EvalCommand argument) throws IOException {

        IOperation operation = argument.getOperation();
        double[] argumentsArray = argument.getArgumentsArray();
        return operation.action(argumentsArray);

    }
}

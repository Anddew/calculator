package calculator;

import calculator.operations.IOperation;
import calculator.results.CalcArgumentsContainer;

import java.io.IOException;

public class CalculationEngine {

    public double calculate(CalcArgumentsContainer argument) throws IOException {

        IOperation operation = argument.getOperation();
        double[] argumentsArray = argument.getArgumentsArray();
        return operation.action(argumentsArray);

    }
}

package calculator;

import calculator.operation.IOperation;
import calculator.input.EvalCommand;
import calculator.output.EvalResult;

import java.io.IOException;

public class CalculationEngine {

    public EvalResult calculate(EvalCommand argument) throws IOException {

        IOperation operation = argument.getOperation();
        double[] argumentsArray = argument.getArgumentsArray();
        double result = operation.action(argumentsArray);
        return new EvalResult(result);

    }
}

package calculator;

import java.io.IOException;

public class CalculationEngine {

    public double calculate(Argument argument) throws IOException {
        return argument.getOperation().action(argument.getFirstArgument(), argument.getSecondArgument());
    }

}

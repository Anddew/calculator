package main.calculator.operation;

import java.util.HashMap;
import java.util.Map;

public class OperationsMap {

    public static final Map<String, IOperation> operations;
    static {
        operations = new HashMap<>();

        IOperation addition = new Addition();
        operations.put(addition.getName(), addition);

        IOperation subtraction = new Subtraction();
        operations.put(subtraction.getName(), subtraction);

        IOperation multiplication = new Multiplication();
        operations.put(multiplication.getName(), multiplication);

        IOperation division = new Division();
        operations.put(division.getName(), division);

        IOperation power = new PowerOf();
        operations.put(power.getName(), power);

        IOperation logarithm = new Logarithm();
        operations.put(logarithm.getName(), logarithm);
    }

}

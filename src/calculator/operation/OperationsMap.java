package calculator.operation;

import java.util.HashMap;
import java.util.Map;

public class OperationsMap {

    public static final Map<String, SequentialOperation> operations;
    static {
        operations = new HashMap<>();

        SequentialOperation addition = new Addition();
        operations.put(addition.getName(), addition);

        SequentialOperation subtraction = new Subtraction();
        operations.put(subtraction.getName(), subtraction);

        SequentialOperation multiplication = new Multiplication();
        operations.put(multiplication.getName(), multiplication);

        SequentialOperation division = new Division();
        operations.put(division.getName(), division);

        SequentialOperation power = new PowerOf();
        operations.put(power.getName(), power);

        SequentialOperation logarithm = new Logarithm();
        operations.put(logarithm.getName(), logarithm);
    }

}

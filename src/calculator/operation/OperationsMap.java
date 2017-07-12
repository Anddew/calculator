package calculator.operation;

import java.util.HashMap;
import java.util.Map;

public class OperationsMap {

    public static final Map<Character, IOperation> operations;
    static {
        operations = new HashMap<>();

        IOperation addition = new Addition();
        operations.put(addition.getSign(), addition);

        IOperation subtraction = new Subtraction();
        operations.put(subtraction.getSign(), subtraction);

        IOperation multiplication = new Multiplication();
        operations.put(multiplication.getSign(), multiplication);

        IOperation division = new Division();
        operations.put(division.getSign(), division);
    }

}

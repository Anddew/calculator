package calculator.operation;

import java.util.HashMap;
import java.util.Map;

public class OperationsMap {

    private Map<Character, IOperation> operations;

    public OperationsMap() {
        operations = new HashMap<>();
        operations.put(new Addition().getSign(), new Addition());
        operations.put(new Division().getSign(), new Division());
        operations.put(new Multiplication().getSign(), new Multiplication());
        operations.put(new Subtraction().getSign(), new Subtraction());
    }

    public Map<Character, IOperation> getOperationsMap() {
        return operations;
    }

}

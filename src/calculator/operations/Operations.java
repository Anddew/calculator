package calculator.operations;

import java.util.ArrayList;
import java.util.List;

public class Operations {

    private List<IOperation> operations;

    public Operations() {
        operations = new ArrayList<>();
        operations.add(new Addition());
        operations.add(new Division());
        operations.add(new Multiplication());
        operations.add(new Subtraction());
    }

    public List<IOperation> getListOperations() {
        return operations;
    }

}

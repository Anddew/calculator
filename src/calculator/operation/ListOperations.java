package calculator.operation;

import java.util.ArrayList;
import java.util.List;

public class ListOperations {

    private List<IOperation> operations;

    public ListOperations() {
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

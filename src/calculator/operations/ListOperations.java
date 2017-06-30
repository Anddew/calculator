package calculator.operations;

import java.util.ArrayList;
import java.util.List;

public class ListOperations {

    private List<Operation> operations;

    public ListOperations() {
        operations = new ArrayList<>();
        operations.add(new Addition());
        operations.add(new Division());
        operations.add(new Multiplication());
        operations.add(new Subtraction());
    }

    public List<Operation> getListOperations() {
        return operations;
    }



}

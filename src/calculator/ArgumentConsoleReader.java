package calculator;

import calculator.operations.IOperation;
import calculator.operations.Operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArgumentConsoleReader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Argument readArgumentConsole() throws IOException {
        Argument result = new Argument();
        System.out.println("Input first argument...");
        result.setFirstArgument(Double.parseDouble(reader.readLine()));
        System.out.println("Choose operation...");
        result.setOperation(chooseOperation());
        System.out.println("Input second argument...");
        result.setSecondArgument(Double.parseDouble(reader.readLine()));
        return result;
    }
    
    private IOperation chooseOperation() throws IOException {
        Operations operations = new Operations();
        byte operationNumber = 0;
        for(IOperation elem: operations.getListOperations()) {
            System.out.println("Type " + operationNumber++ + " to use operation - " + elem.getClass().getName());
        }
        byte chosenOperation = Byte.parseByte(reader.readLine());
        if (chosenOperation < 0 || chosenOperation > operationNumber) {
            System.out.println("Invalid input number for choosing operation");
            throw new IllegalArgumentException();
        }
        return operations.getListOperations().get(chosenOperation);
    }

}

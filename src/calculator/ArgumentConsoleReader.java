package calculator;

import calculator.operations.Operation;
import calculator.operations.ListOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArgumentConsoleReader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
    public CalcArgumentsContainer readArgumentConsole() throws IOException {
        System.out.println("Input first argument...");
        double firstArgument = Double.parseDouble(reader.readLine());
        System.out.println("Choose operation...");
        IOperation operation = chooseOperation();
        System.out.println("Input second argument...");
        double secondArgument = Double.parseDouble(reader.readLine());
        return new CalcArgumentsContainer(firstArgument, operation, secondArgument);
    }
     */

    public CalcArgumentsContainer readArgumentsConsole() throws IOException {
        System.out.println("Input math expression like +(1 2 3) or *(8 3 2.5)");
        String expression = reader.readLine();

        if (expression.charAt(1) != '(' || expression.charAt(expression.length() - 1) != ')') {
            throw new IllegalArgumentException("Invalid input");
        }

        Operation operation = chooseOperation(expression.charAt(0));
        String argumentsString = expression.substring(2, expression.length() - 1);
        double[] arguments = obtainArgumentsArray(argumentsString);
        return new CalcArgumentsContainer(arguments, operation);
    }

    private double[] obtainArgumentsArray(String expression) {
        String[] valuesString = expression.split(" ");
        if (valuesString.length > 20) {
            throw new IllegalArgumentException("Input not more than 20 arguments");
        }
        double[] result = new double[valuesString.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = Double.parseDouble(valuesString[i]);
        }
        return result;
    }

    private Operation chooseOperation(char check) {
        ListOperations listOperations = new ListOperations();
        for (Operation elem: listOperations.getListOperations()) {
            if (elem.getSign() == check)
                return elem;
        }
        throw new IllegalArgumentException("Invalid math sign");
    }

    private Operation chooseOperation() throws IOException {
        ListOperations listOperations = new ListOperations();
        byte operationNumber = 0;
        for(Operation elem: listOperations.getListOperations()) {
            System.out.println("Type " + operationNumber++ + " to use operation - " + elem.getClass().getName());
        }
        byte chosenOperation = Byte.parseByte(reader.readLine());
        if (chosenOperation < 0 || chosenOperation > operationNumber) {
            System.out.println("Invalid input number for choosing operation");
            throw new IllegalArgumentException();
        }
        return listOperations.getListOperations().get(chosenOperation);
    }

}

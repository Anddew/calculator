package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private static List<IOperation> operations;

    public Calculator() {
        operations = new ArrayList<>();
        operations.add(new Addition());
        operations.add(new Subtraction());
        operations.add(new Multiplication());
        operations.add(new Division());
    }

    public static void main(String[] args) throws IOException {
        calculate();
    }

    public static void calculate() throws IOException {
        new Calculator();
        while (true) {
            System.out.println("Press Enter to continue... or input 'quit' to exit ");
            if (input().equals("quit"))
                break;
            System.out.println(chooseOperation().action(inputArgument(), inputArgument()));
        }
    }

    private static double inputArgument() throws IOException {
        System.out.println("Input argument...");
        return Double.parseDouble(input());
    }

    private static IOperation chooseOperation() throws IOException {
        byte operationNumber = 0;
        for(IOperation elem: operations) {
            System.out.println("Type " + operationNumber++ + " to use operation - " + elem.getClass().getName());
        }
        int numberAction = Integer.parseInt(input());
        if (numberAction < 0 || numberAction > operationNumber) {
            System.out.println("Invalid input number for choosing operation");
            throw new IllegalArgumentException();
        }
        return operations.get(numberAction);
    }

    private static String input() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

}

package calculator;

import calculator.operations.IOperation;
import calculator.results.Helper;
import calculator.results.ICommand;
import calculator.operations.ListOperations;
import calculator.results.AppBreak;
import calculator.results.CalcArgumentsContainer;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandReader {

    private Scanner scanner = new Scanner(System.in);

    public ICommand readCommand() throws IOException {

        boolean quitCondition = false;
        while ( !quitCondition ) {
            String expression = scanner.nextLine().trim();
            if (!expression.equals("quit")) {
                String expressionValidatorRegex = "([+\\-*/])\\(\\s*((\\d+\\.?\\d*\\s*)*)\\)";
                Pattern expressionValidator = Pattern.compile(expressionValidatorRegex);
                Matcher matcher = expressionValidator.matcher(expression);
                if (matcher.matches()) {
                    IOperation operation = chooseOperation(matcher.group(1).charAt(0));
                    double[] argumentsArray = convertStringArrayToDoubleArray(matcher.group(2).split("\\s+"));
                    return new CalcArgumentsContainer(argumentsArray, operation);
                } else {
                    return new Helper();
                }
            } else {
                quitCondition = true;
            }
        } return new AppBreak();
    }

    private double[] convertStringArrayToDoubleArray(String[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Double.parseDouble(array[i]);
        }
        return result;
    }

    private IOperation chooseOperation(char check) {
        ListOperations listOperations = new ListOperations();
        for (IOperation elem: listOperations.getListOperations()) {
            if (elem.getSign() == check)
                return elem;
        }
        throw new IllegalArgumentException("Invalid math sign");
    }

    private IOperation chooseOperation() throws IOException {
        ListOperations listOperations = new ListOperations();
        byte operationNumber = 0;
        for(IOperation elem: listOperations.getListOperations()) {
            System.out.println("Type " + operationNumber++ + " to use operation - " + elem.getClass().getName());
        }
        byte chosenOperation = Byte.parseByte(scanner.nextLine());
        if (chosenOperation < 0 || chosenOperation > operationNumber) {
            throw new IllegalArgumentException("Invalid input number for choosing operation");
        }
        return listOperations.getListOperations().get(chosenOperation);
    }

}

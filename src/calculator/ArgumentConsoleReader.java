package calculator;

import calculator.operations.Addition;
import calculator.operations.Operation;
import calculator.operations.ListOperations;
import calculator.results.AppBreak;
import calculator.results.CalcArgumentsContainer;
import calculator.results.ResultOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentConsoleReader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ResultOperation readArgumentsConsole() throws IOException {
        Operation operation;
        double[] argumentsArray;
        String expression;
        String expressionValidatorRegex = "([+\\-*/])\\(\\s*((\\d+\\.?\\d*\\s*)*)\\)";
        String valuesArrayTerminatorRegex = "\\s+";

        while (true) {
            System.out.println("Input math expression like +(1 2 3) or *(8 3 2.5) or 'quit' to exit");
            expression = reader.readLine().trim();
            if (!expression.equals("quit")) {
                Matcher matcher = validateInputExpression(expressionValidatorRegex, expression);
                if (matcher.matches()) {
                    operation = chooseOperation(matcher.group(1).charAt(0));
                    String values = matcher.group(2);
                    String[] argumentsStringArray = splitExpressionToStringArray(valuesArrayTerminatorRegex, values);
                    argumentsArray = convertStringArrayToDoubleArray(argumentsStringArray);
                    return new CalcArgumentsContainer(argumentsArray, operation);
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            } else return new AppBreak();
        }
    }

    private String[] splitExpressionToStringArray (String regex, String expression) {
        Pattern valuesArrayTerminator = Pattern.compile(regex);
        return valuesArrayTerminator.split(expression);
    }

    private Matcher validateInputExpression(String regex, String expression) {
        Pattern expressionValidator = Pattern.compile(regex);
        return expressionValidator.matcher(expression);
    }

    private double[] convertStringArrayToDoubleArray(String[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Double.parseDouble(array[i]);
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
            throw new IllegalArgumentException("Invalid input number for choosing operation");
        }
        return listOperations.getListOperations().get(chosenOperation);
    }

}

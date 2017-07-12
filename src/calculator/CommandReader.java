package calculator;

import calculator.input.*;
import calculator.operation.IOperation;
import calculator.operation.ListOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandReader {

    private Scanner scanner = new Scanner(System.in);

    public ICommand readCommand() throws IOException {
        String expression = scanner.nextLine().trim();
        switch (expression) {
            case "quit":
                return new QuitCommand();
            case "help":
                return new HelpCommand();
            default:
                String expressionValidatorRegex = "\\s*eval\\s+([+\\-*/])\\(\\s*((\\d+\\.?\\d*\\s*)*)\\)";
                Pattern expressionValidator = Pattern.compile(expressionValidatorRegex);
                Matcher matcher = expressionValidator.matcher(expression);
                if (matcher.matches()) {
                    IOperation operation = chooseOperation(matcher.group(1).charAt(0));
                    String[] argumentsStringArray = matcher.group(2).split("\\s+");
                    if (argumentsStringArray.length > 20) {
                        return new InvalidInput("Invalid input. Operation can have no more than 20 operands");
                    } else {
                        List<Double> argumentsList = convertStringArrayToDoubleList(argumentsStringArray);
                        return new EvalCommand(argumentsList, operation);
                    }
                } else {
                    return new InvalidInput("Invalid input. Please try again. For help type 'help'.");
                }
        }
    }

    private List<Double> convertStringArrayToDoubleList(String[] array) {
        List<Double> result = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            result.add(Double.parseDouble(array[i]));
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

}

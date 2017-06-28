package calculator;

import calculator.operations.*;

import java.io.IOException;

public class ConsoleCalculator {

    private CalculationEngine engine;
    private InformationConsoleReader reader;
    private InformationWriter writer;

    public ConsoleCalculator() {
        engine = new CalculationEngine();
        reader = new InformationConsoleReader();
        writer = new InformationWriter();
    }

    public void startConsoleCalculator() throws IOException {
        while (true) {
            writer.writeToConsole("Press Enter or type 'quit' to exit");
            if (reader.readLine().equals("quit")) {
                break;
            } else {
                writer.writeToConsole("Input first argument");
                double arg1 = reader.readArgument();
                writer.writeToConsole("Choose operation");
                IOperation operation = chooseOperation();
                writer.writeToConsole("Input second argument");
                double arg2 = reader.readArgument();
                double result = engine.calculate(arg1, operation, arg2);
                writer.writeToConsole("Result = " + result);
            }
        }
    }

    private IOperation chooseOperation() throws IOException {
        byte operationNumber = 0;
        for(IOperation elem: engine.operations) {
            System.out.println("Type " + operationNumber++ + " to use operation - " + elem.getClass().getName());
        }
        int numberAction = Integer.parseInt(reader.readLine());
        if (numberAction < 0 || numberAction > operationNumber) {
            System.out.println("Invalid input number for choosing operation");
            throw new IllegalArgumentException();
        }
        return engine.operations.get(numberAction);
    }

}

package calculator;

import java.io.IOException;

public class ConsoleCalculator {

    private CalculationEngine engine;
    private ArgumentConsoleReader reader;
    private ResultConsoleWriter writer;

    public ConsoleCalculator() {
        engine = new CalculationEngine();
        reader = new ArgumentConsoleReader();
        writer = new ResultConsoleWriter();
    }

    public void startConsoleCalculator() throws IOException {
        CalcArgumentsContainer argument = reader.readArgumentsConsole();
        double result = engine.calculate(argument);
        writer.writeToConsole(result);
    }

}

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
        writer.writeToConsole(engine.calculate(reader.readArgumentConsole()));


    }



}

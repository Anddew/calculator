package calculator;

import calculator.results.Markable;
import calculator.results.CalcArgumentsContainer;
import calculator.results.ResultMarker;

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
        Markable resultInput;
        while ((resultInput = reader.readArgumentsConsole()).getResultMarker() == ResultMarker.ArgumentsContainer ) {
            double result = engine.calculate((CalcArgumentsContainer) resultInput);
            writer.writeToConsole(result);
        }
    }
}
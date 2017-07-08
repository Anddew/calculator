package calculator;

import calculator.results.CalcArgumentsContainer;
import calculator.results.ResultOperation;

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
        ResultOperation resultOperation = reader.readArgumentsConsole();
        switch (resultOperation.getResultMarker()) {
            case ArgumentsContainer: {
                CalcArgumentsContainer argument = (CalcArgumentsContainer) resultOperation;
                double result = engine.calculate(argument);
                writer.writeToConsole(result);
            } break;
            case AppBreak: {
                //NOP
            } break;
        }

    }

}

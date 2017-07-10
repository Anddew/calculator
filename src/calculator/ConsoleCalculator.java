package calculator;

import calculator.results.CommandMarker;
import calculator.results.ICommand;
import calculator.results.CalcArgumentsContainer;

import java.io.IOException;

public class ConsoleCalculator {

    private CalculationEngine engine;
    private CommandReader reader;
    private ResultConsoleWriter writer;

    public ConsoleCalculator() {
        engine = new CalculationEngine();
        reader = new CommandReader();
        writer = new ResultConsoleWriter();
    }

    public void startConsoleCalculator() throws IOException {
        ICommand resultInput;
        boolean quitCondition = false;
        while (!quitCondition) {
            switch ( (resultInput = reader.readCommand()).getCommandMarker() ) {
                case EVAL_MARKER:
                    double result = engine.calculate((CalcArgumentsContainer) resultInput);
                    writer.writeToConsole(result);
                    break;
                case QUIT_MARKER:
                    quitCondition = true;
                    break;
                case HELP_MARKER:
                    System.out.println("Invalid input. Please, try again. Input math expression like +(1 2 3) or *(8 3 2.5) or 'quit' to exit");
                    break;
            }
        }
    }
}
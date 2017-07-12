package calculator;

import calculator.input.ICommand;
import calculator.input.EvalCommand;
import calculator.input.InvalidInputCommand;

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

        boolean quitCondition = false;
        while (!quitCondition) {
            ICommand resultInput = reader.readCommand();
            switch ( resultInput.getCommandMarker() ) {
                case EVAL_MARKER:
                    writer.write("" + engine.calculate((EvalCommand) resultInput));
                    break;
                case QUIT_MARKER:
                    quitCondition = true;
                    break;
                case HELP_MARKER:
                    writer.write("Input math expression like 'eval +(1 2 3)' or 'eval *(8 3 2.5)' or 'quit' to exit");
                    break;
                case ILLEGAL_INPUT_MARKER:
                    writer.write( ((InvalidInputCommand) resultInput).getComment() );
                    break;
            }
        }
    }
}
package calculator;

import calculator.input.ICommand;
import calculator.input.EvalCommand;
import calculator.output.BreakResult;
import calculator.output.EvalResult;
import calculator.output.HelpResult;
import calculator.output.IResult;

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
            IResult resultOutput;
            switch ( resultInput.getCommandMarker() ) {
                case EVAL_MARKER:
                    resultOutput = engine.calculate((EvalCommand) resultInput);
                    break;
                case QUIT_MARKER:
                    quitCondition = true;
                    resultOutput = new BreakResult();
                    break;
                case HELP_MARKER:
                    resultOutput = new HelpResult();
                    break;
                default:
                    resultOutput = new HelpResult();
                    break;
            }
            writer.write(resultOutput);
        }
    }
}
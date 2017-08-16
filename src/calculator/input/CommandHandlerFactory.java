package calculator.input;

import calculator.ICalculatorContext;
import calculator.input.command.ICommand;
import calculator.input.commandlogic.*;

public class CommandHandlerFactory {

    public ICommandLogic getLogic(ICommand command, ICalculatorContext calculatorContext) {
        switch (command.getCommandMarker()) {
            case EVAL_MARKER: return new EvalCommandLogic(calculatorContext);
            case QUIT_MARKER: return new QuitCommandLogic(calculatorContext);
            case HELP_MARKER: return new HelpCommandLogic(calculatorContext);
            case INVALID_INPUT_MARKER: return new InvalidInputLogic(calculatorContext);
            default: throw new RuntimeException("Missed switch case for unknown command type.");
        }
    }

}
package calculator.input;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.*;
import calculator.input.commandlogic.*;

public class CommandHandlerFactory {

    public ICommandLogic getLogic(ICommand command, CalculatorContext calculatorContext) {
        switch (command.getCommandMarker()) {
            case EVAL_MARKER: return new EvalCommandLogic((EvalCommand) command, calculatorContext);
            case QUIT_MARKER: return new QuitCommandLogic((QuitCommand) command, calculatorContext);
            case HELP_MARKER: return new HelpCommandLogic((HelpCommand) command, calculatorContext);
            case INVALID_INPUT_MARKER: return new InvalidInputLogic((InvalidInput) command, calculatorContext);
            default: throw new RuntimeException("Missed switch case for unknown command type.");
        }
    }

}
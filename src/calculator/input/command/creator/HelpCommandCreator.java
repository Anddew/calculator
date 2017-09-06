package calculator.input.command.creator;

import calculator.input.command.HelpCommand;
import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;

public class HelpCommandCreator implements ICommandCreator {

    @Override
    public ICommand createCommand(int prefixLength, String arguments) {
        if (arguments.isEmpty()) {
            return new HelpCommand();
        } else {
            return new InvalidInput("Invalid input. Expression has wrong format: help command must have no arguments.");
        }
    }

}

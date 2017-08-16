package calculator.input.command.creator;

import calculator.input.command.HelpCommand;
import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;

public class HelpCommandCreator implements ICommandCreator {

    @Override
    public ICommand createCommand(String arguments) {
        if (arguments == null) {
            return new HelpCommand();
        } else {
            return new InvalidInput("Invalid input. Help command must have no arguments.");
        }
    }

}

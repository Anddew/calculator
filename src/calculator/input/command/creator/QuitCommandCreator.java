package calculator.input.command.creator;

import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;
import calculator.input.command.QuitCommand;

public class QuitCommandCreator implements ICommandCreator {

    @Override
    public ICommand createCommand(String arguments) {
        if (arguments.isEmpty()) {
            return new QuitCommand();
        } else {
            return new InvalidInput("Invalid input. Quit command must have no arguments.");
        }
    }

}

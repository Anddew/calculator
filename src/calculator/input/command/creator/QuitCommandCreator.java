package calculator.input.command.creator;

import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;
import calculator.input.command.QuitCommand;

public class QuitCommandCreator implements ICommandCreator {

    @Override
    public ICommand createCommand(int prefixLength, String arguments) {
        if (arguments.isEmpty()) {
            return new QuitCommand();
        } else {
            return new InvalidInput("Invalid input. Expression has wrong format: quit command must have no arguments.");
        }
    }

}

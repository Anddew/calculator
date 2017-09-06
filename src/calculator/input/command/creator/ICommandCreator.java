package calculator.input.command.creator;

import calculator.input.command.ICommand;

public interface ICommandCreator {

    ICommand createCommand(int prefixLength, String arguments);
}

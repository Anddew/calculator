package main.calculator.command.creator;

import main.calculator.command.HelpCommand;
import main.calculator.command.ICommand;
import main.calculator.command.InvalidInput;

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

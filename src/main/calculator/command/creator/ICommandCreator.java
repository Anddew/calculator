package main.calculator.command.creator;

import main.calculator.command.ICommand;

public interface ICommandCreator {

    ICommand createCommand(int prefixLength, String arguments);
}

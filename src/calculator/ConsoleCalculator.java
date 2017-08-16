package calculator;

import calculator.input.CommandHandlerFactory;
import calculator.input.command.CommandMarker;
import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;
import calculator.reader.ConsoleCommandReader;

import java.io.IOException;

class ConsoleCalculator {

    private CommandHandlerFactory factory = new CommandHandlerFactory();
    private ConsoleCalculatorContext calculatorContext = new ConsoleCalculatorContext();
    private ConsoleCommandReader commandReader = new ConsoleCommandReader();

    void startConsoleCalculator() {
        ICommand command;
        while (! (command = commandReader.readCommand()).getCommandMarker().equals(CommandMarker.QUIT_MARKER) ) {
            factory.getLogic(command, calculatorContext).useLogic(command);
        }
    }
}
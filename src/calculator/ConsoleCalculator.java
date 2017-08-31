package calculator;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.CommandHandlerFactory;
import calculator.input.command.CommandsMap;
import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;
import calculator.input.command.creator.ICommandCreator;
import calculator.reader.ConsoleReader;
import calculator.reader.IReader;
import calculator.writer.ConsoleWriter;

class ConsoleCalculator {

    private CommandHandlerFactory factory = new CommandHandlerFactory();
    private CalculatorContext calculatorContext = new CalculatorContext(new ConsoleWriter());
    private IReader reader = new ConsoleReader();


    void startConsoleCalculator() {
        while(!calculatorContext.isQuitCondition() ) {
            ICommand command;
            String input = reader.read();
            String[] commandParts = input.split("\\s+|$", 2);
            ICommandCreator commandCreator = CommandsMap.commands.get(commandParts[0]);
            if(commandCreator != null) {
                command = commandCreator.createCommand(commandParts[1]);
            } else {
                command = new InvalidInput("Invalid input. Invalid command name.");
            }
            factory.getLogic(command, calculatorContext).useLogic();
        }
    }
}
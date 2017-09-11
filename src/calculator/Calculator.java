package calculator;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.CommandHandlerFactory;
import calculator.input.command.CommandsMap;
import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;
import calculator.input.command.creator.ICommandCreator;
import calculator.reader.ConsoleReader;
import calculator.reader.FileReader;
import calculator.reader.IReader;
import calculator.writer.ConsoleWriter;
import calculator.writer.IWriter;

import java.io.FileWriter;
import java.io.IOException;

class Calculator {

    private CommandHandlerFactory factory = new CommandHandlerFactory();
    private CalculatorContext calculatorContext;
    private IReader reader;

    public Calculator(IReader reader, IWriter writer) {
        this.reader = reader;
        this.calculatorContext = new CalculatorContext(writer);
    }

    void startCalculator() {
        while ( !calculatorContext.isQuitCondition() ) {
            try {
                ICommand command;
                String input = reader.read();
                if(!input.isEmpty()) {
                    String[] commandParts = input.split("\\s+|$", 2);
                    ICommandCreator commandCreator = CommandsMap.commands.get(commandParts[0]);
                    if(commandCreator != null) {
                        command = commandCreator.createCommand(input.length() - commandParts[1].length(), commandParts[1]);
                    } else {
                        command = new InvalidInput("Invalid input. Invalid command name. ('" + commandParts[0] + "':" + (input.length() - commandParts[1].length()) + ")");
                    }
                    factory.getLogic(command, calculatorContext).useLogic();
                }
            } catch (IOException e) {

            }
        }
    }
}
package main.calculator;

import main.calculator.command.logic.CommandLogicFactory;
import main.calculator.command.CommandsMap;
import main.calculator.command.ICommand;
import main.calculator.command.InvalidInput;
import main.calculator.command.creator.ICommandCreator;
import main.calculator.input.IReader;
import main.calculator.output.IWriter;

class Calculator {

    private CommandLogicFactory factory = new CommandLogicFactory();
    private CalculatorContext calculatorContext;
    private IReader reader;
    private IWriter writer;

    public Calculator(IReader reader, IWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.calculatorContext = new CalculatorContext(writer);
    }

    void startCalculator() {
        while ( !calculatorContext.isQuitCondition() ) {
            ICommand command;
            String input = reader.read();
            if(input != null) {
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
            } else {
                calculatorContext.setQuitCondition();
            }
        }
        reader.close();
        writer.close();
    }
}
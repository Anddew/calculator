package main.calculator.command.creator;

import main.calculator.CalculatorContext;
import main.calculator.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandCreatorFactory {

    private final Map<String, ICommandCreator> commands = new HashMap<>();

    public CommandCreatorFactory() {
        commands.put(EvalCommand.getName(), new EvalCommandCreator());
        commands.put(HelpCommand.getName(), new HelpCommandCreator());
        commands.put(QuitCommand.getName(), new QuitCommandCreator());
    }

    public ICommand createCommand(String input, CalculatorContext calculatorContext) {
        String[] commandParts = input.split("\\s+|$", 2);
        String commandName = commandParts[0];
        String commandArgs = commandParts[1];
        ICommandCreator commandCreator = commands.get(commandName);
        if(commandCreator == null) {
            return new InvalidInput("Invalid input. Invalid command name. ('" + commandParts[0] + "':" + (input.length() - commandParts[1].length()) + ")", calculatorContext);
        }
        return commandCreator.createCommand(input.length() - commandArgs.length(), commandArgs, calculatorContext);
    }

}

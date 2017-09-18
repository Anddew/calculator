package main.calculator.command;

import main.calculator.command.creator.EvalCommandCreator;
import main.calculator.command.creator.HelpCommandCreator;
import main.calculator.command.creator.ICommandCreator;
import main.calculator.command.creator.QuitCommandCreator;

import java.util.HashMap;
import java.util.Map;

public class CommandsMap {

    public static final Map<String, ICommandCreator> commands = new HashMap<>();

    static {
        commands.put(EvalCommand.getName(), new EvalCommandCreator());
        commands.put(HelpCommand.getName(), new HelpCommandCreator());
        commands.put(QuitCommand.getName(), new QuitCommandCreator());
    }

}
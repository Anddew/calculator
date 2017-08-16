package calculator.input.command;

import calculator.input.command.creator.EvalCommandCreator;
import calculator.input.command.creator.HelpCommandCreator;
import calculator.input.command.creator.ICommandCreator;
import calculator.input.command.creator.QuitCommandCreator;

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
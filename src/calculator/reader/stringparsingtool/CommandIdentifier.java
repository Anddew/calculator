package calculator.reader.stringparsingtool;

import calculator.input.command.*;
import calculator.input.command.creator.ICommandCreator;
import calculator.reader.IReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandIdentifier {

    private IReader reader;

    public CommandIdentifier(IReader reader) {
        this.reader = reader;
    }

    public ICommand readCommand() {
        String command = reader.read();
        Pattern commandPattern = Pattern.compile("([a-zA-Z]+)(\\s+.+)?");
        Matcher matcher = commandPattern.matcher(command);
        matcher.find();
        ICommandCreator commandCreator = CommandsMap.commands.get(matcher.group(1));
        if (commandCreator != null) {
            return commandCreator.createCommand(matcher.group(2));
        } else {
            return new InvalidInput("Invalid input. Invalid command name.");
        }

    }

}
package calculator.reader;

import calculator.input.command.*;
import calculator.input.command.creator.ICommandCreator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CommandReader {

    ICommand readCommand(String command) {
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
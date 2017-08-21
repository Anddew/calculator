//package calculator.reader.stringparsingtool;
//
//import calculator.input.command.*;
//import calculator.input.command.creator.ICommandCreator;
//import calculator.reader.IReader;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class CommandIdentifier {
//
//    private IReader reader;
//
//    public CommandIdentifier(IReader reader) {
//        this.reader = reader;
//    }
//
//    public ICommand readCommand() {
//        String command = reader.read();
//        String[] commandParts = command.split("\\s+|$", 2);
//        ICommandCreator commandCreator = CommandsMap.commands.get(commandParts[0]);
//        if (commandCreator != null) {
//            return commandCreator.createCommand(commandParts[1]);
//        } else {
//            return new InvalidInput("Invalid input. Invalid command name.");
//        }
//
//    }
//
//}
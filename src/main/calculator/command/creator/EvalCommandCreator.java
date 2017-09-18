package main.calculator.command.creator;

import main.calculator.command.ICommand;
import main.calculator.command.InvalidInput;
import main.calculator.command.parsing.ParsingMachine;
import main.calculator.command.parsing.ReaderAccumulator;
import main.calculator.command.parsing.ReaderStateType;

import java.util.stream.Collectors;

public class EvalCommandCreator implements ICommandCreator {

    @Override
    public ICommand createCommand(int prefixLength, String arguments) {
        if (!arguments.isEmpty()) {
            arguments = arguments.trim().concat("\n");
            ReaderAccumulator readerAccumulator = new ReaderAccumulator(prefixLength);
            ParsingMachine parsingMachine = new ParsingMachine(ReaderStateType.READING, readerAccumulator);
            parsingMachine.handle(arguments.chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
            ICommand command = parsingMachine.getAccumulator().getCommand();
            if (command != null) {
                return command;
            } else {
                return new InvalidInput("Invalid input. Expression has wrong format.");
            }
        } else {
            return new InvalidInput("Invalid input. Eval command must have arguments. For more information type \"help\".");
        }
    }

}
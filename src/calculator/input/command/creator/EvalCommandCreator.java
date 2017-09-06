package calculator.input.command.creator;

import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;
import calculator.input.stringparsingtool.ParsingMachine;
import calculator.input.stringparsingtool.ReaderAccumulator;
import calculator.input.stringparsingtool.ReaderStateType;

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
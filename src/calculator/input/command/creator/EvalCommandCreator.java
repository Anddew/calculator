package calculator.input.command.creator;

import calculator.input.command.EvalCommand;
import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;
import calculator.reader.stringparsingtool.ParsingMachine;
import calculator.reader.stringparsingtool.ReaderAccumulator;
import calculator.reader.stringparsingtool.ReaderState;

import java.util.stream.Collectors;

public class EvalCommandCreator implements ICommandCreator {

    @Override
    public ICommand createCommand(String arguments) {
        if (arguments != null) {
            return parseEvalCommandArguments(arguments.trim());
        } else {
            return new InvalidInput("Eval command must have arguments. For more information type \"help\".");
        }
    }

    private ICommand parseEvalCommandArguments(String arguments) {
        ReaderAccumulator readerAccumulator = new ReaderAccumulator();
        ParsingMachine parsingMachine = new ParsingMachine(ReaderState.READ_OPERATION, readerAccumulator);
        parsingMachine.handle(arguments.chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
        ICommand command = parsingMachine.getAccumulator().getCommand();
        if (command == null) {
            return new EvalCommand(parsingMachine.getAccumulator().getElementsList());
        } else return command;
    }

}

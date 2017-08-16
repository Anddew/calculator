package calculator.reader.stringparsingtool;

import calculator.input.command.ICommand;
import calculator.input.command.evalcommandtoken.IEvalCommandToken;

import java.util.ArrayList;
import java.util.List;

public class ReaderAccumulator {

    private StringBuffer buffer = new StringBuffer();

    StringBuffer getBuffer() {
        return buffer;
    }

    private List<IEvalCommandToken> elementsList = new ArrayList<>();

    public List<IEvalCommandToken> getElementsList() {
        return elementsList;
    }

    private ICommand command;

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }
}

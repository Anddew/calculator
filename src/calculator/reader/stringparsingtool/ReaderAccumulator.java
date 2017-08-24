package calculator.reader.stringparsingtool;

import calculator.input.command.ICommand;
import calculator.input.command.evalcommandtoken.IEvalCommandToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReaderAccumulator {

    public ReaderAccumulator() {
        stack.add(Limiter.createLimiter());
    }

    private StringBuffer buffer = new StringBuffer();

    StringBuffer getBuffer() {
        return buffer;
    }

    private List<IEvalCommandToken> elementsList = new ArrayList<>();

    public List<IEvalCommandToken> getElementsList() {
        return elementsList;
    }

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private ICommand command;

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    private Stack<Limiter> stack = new Stack<>();

    public Stack<Limiter> getStack() {
        return stack;
    }


}

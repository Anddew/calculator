package calculator.input.stringparsingtool;

import calculator.input.command.ICommand;
import calculator.input.command.creator.evalcommandtoken.IEvalCommandToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReaderAccumulator {

    public ReaderAccumulator() {
        operandCounterStack.add(new OperandCounter());
        boundsStack.add(new Bounds(1, 1));
    }

    private StringBuffer buffer = new StringBuffer();

    StringBuffer getBuffer() {
        return buffer;
    }

    private List<IEvalCommandToken> elementsList = new ArrayList<>();

    List<IEvalCommandToken> getElementsList() {
        return elementsList;
    }

    private ICommand command;

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    private Stack<OperandCounter> operandCounterStack = new Stack<>();
    private Stack<Bounds> boundsStack = new Stack<>();

    Stack<Bounds> getBoundsStack() {
        return boundsStack;
    }

    Stack<OperandCounter> getOperandCounterStack() {
        return operandCounterStack;
    }

    int numberEndTokenExpected;

}

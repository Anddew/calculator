package calculator.input.stringparsingtool;

import calculator.input.command.ICommand;
import calculator.input.command.creator.evalcommandtoken.IEvalCommandToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReaderAccumulator {

    private StringBuffer buffer = new StringBuffer();
    private List<IEvalCommandToken> elementsList = new ArrayList<>();
    private ICommand command;
    private Stack<OperandCounter> operandCounterStack = new Stack<>();
    private Stack<Bounds> boundsStack = new Stack<>();
    int numberEndTokenExpected;

    public ReaderAccumulator() {
        operandCounterStack.add(new OperandCounter());
        boundsStack.add(new Bounds(1, 1));
    }

    StringBuffer getBuffer() {
        return buffer;
    }

    List<IEvalCommandToken> getElementsList() {
        return elementsList;
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    Stack<Bounds> getBoundsStack() {
        return boundsStack;
    }

    Stack<OperandCounter> getOperandCounterStack() {
        return operandCounterStack;
    }

}

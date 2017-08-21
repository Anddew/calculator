package calculator.input.commandlogic;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.EvalCommand;
import calculator.input.command.evalcommandtoken.*;
import calculator.operation.IOperation;

import java.util.*;

import static calculator.input.command.evalcommandtoken.EvalCommandTokenType.VALUE;

public class EvalCommandLogic implements ICommandLogic {

    private CalculatorContext calculatorContext;
    private EvalCommand command;

    public EvalCommandLogic(EvalCommand command, CalculatorContext calculatorContext) {
        this.command = command;
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic() {
        Stack<IEvalCommandToken> stack = new Stack<>();
        for(IEvalCommandToken elem: command.getElementsList()) {
            switch (elem.getTokenType()) {
                case OPERATION_END: {
                    LinkedList<Double> result = new LinkedList<>();
                    IOperation operation = null;
                    do {
                        IEvalCommandToken next = stack.pop();
                        if(next.getTokenType().equals(VALUE)) {
                            result.addFirst(((ValueToken) next).getValue());
                        } else {
                            operation = ((OperationToken) next).getOperation();
                            stack.push(new ValueToken(operation.apply(result)));
                        }
                    } while (operation == null);
                    break;
                }
                case OPERATION:
                case VALUE:
                    stack.push(elem);
                    break;
                default: throw new RuntimeException("Unknown token type.");
            }
        }
        calculatorContext.getWriter().write(((ValueToken) stack.pop()).getValue());

    }
}
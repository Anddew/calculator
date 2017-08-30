package calculator.input.commandlogic;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.EvalCommand;
import calculator.input.command.creator.evalcommandtoken.IEvalCommandToken;
import calculator.input.command.creator.evalcommandtoken.OperationToken;
import calculator.input.command.creator.evalcommandtoken.ValueToken;
import calculator.operation.IOperation;

import java.util.*;

import static calculator.input.command.creator.evalcommandtoken.EvalCommandTokenType.VALUE;

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
        for(IEvalCommandToken token: command.getTokenList()) {
            switch (token.getTokenType()) {
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
                    stack.push(token);
                    break;
                default: throw new RuntimeException("Unknown token type.");
            }
        }
        calculatorContext.getWriter().write(((ValueToken) stack.pop()).getValue());

    }
}
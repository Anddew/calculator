package calculator.input.commandlogic;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.EvalCommand;
import calculator.input.command.creator.evalcommandtoken.ErrorToken;
import calculator.input.command.creator.evalcommandtoken.IEvalCommandToken;
import calculator.input.command.creator.evalcommandtoken.OperationToken;
import calculator.input.command.creator.evalcommandtoken.ValueToken;
import calculator.operation.IOperation;
import calculator.operation.OperationResult;

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
        evaluateCommandContent(stack);
        IEvalCommandToken result = stack.pop();
        if(result.getTokenType().equals(VALUE)) {
            calculatorContext.getWriter().write(((ValueToken) result).getValue());
        } else {
            calculatorContext.getWriter().write(((ErrorToken) result).getErrorMessage());
        }
    }

    private void evaluateCommandContent(Stack<IEvalCommandToken> stack) {
        for(IEvalCommandToken token: command.getTokenList()) {
            switch (token.getTokenType()) {
                case OPERATION_END:
                    LinkedList<Double> result = new LinkedList<>();
                    IOperation operation = null;
                    while (operation == null) {
                        IEvalCommandToken next = stack.pop();
                        if(next.getTokenType().equals(VALUE)) {
                            result.addFirst(((ValueToken) next).getValue());
                        } else {
                            operation = ((OperationToken) next).getOperation();
                        }
                    }
                    OperationResult operationResult = operation.apply(result);
                    if(operationResult.isResult()) {
                        stack.push(new ValueToken(operationResult.getResult()));
                    } else {
                        stack.clear();
                        stack.push(new ErrorToken(operationResult.getErrorMessage()));
                        return;
                    }
                    break;
                case OPERATION:
                case VALUE:
                    stack.push(token);
                    break;
                default: throw new RuntimeException("Unknown token type - " + token.getTokenType());
            }
        }
    }
}
package main.calculator.command;

import main.calculator.CalculatorContext;
import main.calculator.command.creator.evalcommandtoken.ErrorToken;
import main.calculator.command.creator.evalcommandtoken.IEvalCommandToken;
import main.calculator.command.creator.evalcommandtoken.OperationToken;
import main.calculator.command.creator.evalcommandtoken.ValueToken;
import main.calculator.operation.IOperation;
import main.calculator.operation.OperationResult;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static main.calculator.command.creator.evalcommandtoken.EvalCommandTokenType.VALUE;

public class EvalCommand implements ICommand {

    private List<IEvalCommandToken> tokenList;
    private CalculatorContext calculatorContext;

    public EvalCommand(List<IEvalCommandToken> tokenList, CalculatorContext calculatorContext) {
        this.tokenList = tokenList;
        this.calculatorContext = calculatorContext;
    }

    public static String getName() {
        return "eval";
    }

    @Override
    public void execute() {
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
        for(IEvalCommandToken token: tokenList) {
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

package calculator.input.commandlogic;

import calculator.ConsoleCalculatorContext;
import calculator.ICalculatorContext;
import calculator.input.command.EvalCommand;
import calculator.input.command.ICommand;
import calculator.input.command.evalcommandtoken.*;
import calculator.operation.SequentialOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class EvalCommandLogic implements ICommandLogic {

    private ICalculatorContext calculatorContext;

    public EvalCommandLogic(ICalculatorContext calculatorContext) {
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic(ICommand command) {
        EvalCommand evalCommand = (EvalCommand) command;
        Stack<IEvalCommandToken> stack = new Stack<>();
        for(IEvalCommandToken elem: evalCommand.getElementsList()) {
            stack.push(elem);
            if(stack.peek().getTokenType().equals(EvalCommandTokenType.OPERATION_END)) {
                stack.pop();
                List<Double> result = new ArrayList<>();
                while (stack.peek().getTokenType().equals(EvalCommandTokenType.VALUE)) {
                    result.add(((ValueToken)stack.pop()).getValue());
                }
                Collections.reverse(result);
                if(stack.peek().getTokenType().equals(EvalCommandTokenType.OPERATION)) {
                    SequentialOperation operation = ((OperationToken) stack.pop()).getOperation();
                    stack.push(new ValueToken(operation.apply(result)));
                } else {
                    throw new RuntimeException("Unknown Token type.");
                }

            }
        }

        calculatorContext.getWriter().write(((ValueToken) stack.pop()).getValue());

    }
}
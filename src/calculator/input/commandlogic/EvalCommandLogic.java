package calculator.input.commandlogic;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.EvalCommand;
import calculator.input.command.evalcommandtoken.*;
import calculator.operation.IOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
        // TODO: 18.08.2017 сделать через свич и без peek() и реверса 
        for(IEvalCommandToken elem: command.getElementsList()) {
            stack.push(elem);
            if(stack.peek().getTokenType().equals(EvalCommandTokenType.OPERATION_END)) {
                stack.pop();
                List<Double> result = new ArrayList<>();
                while (stack.peek().getTokenType().equals(EvalCommandTokenType.VALUE)) {
                    result.add(((ValueToken)stack.pop()).getValue());
                }
                Collections.reverse(result);
                if(stack.peek().getTokenType().equals(EvalCommandTokenType.OPERATION)) {
                    IOperation operation = ((OperationToken) stack.pop()).getOperation();
                    stack.push(new ValueToken(operation.apply(result)));
                } else {
                    throw new RuntimeException("Unknown Token type.");
                }

            }
        }

        calculatorContext.getWriter().write(((ValueToken) stack.pop()).getValue());

    }
}
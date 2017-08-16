package calculator.input.commandlogic;

import calculator.ICalculatorContext;
import calculator.input.command.ICommand;
import calculator.input.command.InvalidInput;

public class InvalidInputLogic implements ICommandLogic {


    private ICalculatorContext calculatorContext;

    public InvalidInputLogic(ICalculatorContext calculatorContext) {
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic(ICommand command) {
        calculatorContext.getWriter().write( ((InvalidInput)command).getComment() );
    }

}

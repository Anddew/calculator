package main.calculator.command.logic;

import main.calculator.CalculatorContext;
import main.calculator.command.InvalidInput;

public class InvalidInputLogic implements ICommandLogic {

    private CalculatorContext calculatorContext;
    private InvalidInput command;

    public InvalidInputLogic(InvalidInput command, CalculatorContext calculatorContext) {
        this.command = command;
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic() {
        calculatorContext.getWriter().write(command.getComment());
    }

}

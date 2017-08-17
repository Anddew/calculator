package calculator.input.commandlogic;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.InvalidInput;

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

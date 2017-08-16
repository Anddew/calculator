package calculator.input.commandlogic;

import calculator.ICalculatorContext;
import calculator.input.command.ICommand;

public class HelpCommandLogic implements ICommandLogic {

    private ICalculatorContext calculatorContext;
    private String help = "Input math expression like 'eval +(1 2 3)' or 'eval *(8 3 2.5)' or 'quit' to exit";

    public HelpCommandLogic(ICalculatorContext calculatorContext) {
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic(ICommand command) {
        calculatorContext.getWriter().write(help);
    }

}

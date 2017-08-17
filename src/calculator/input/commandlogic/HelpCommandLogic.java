package calculator.input.commandlogic;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.HelpCommand;

public class HelpCommandLogic implements ICommandLogic {

    private CalculatorContext calculatorContext;
    private HelpCommand command;
    private String help = "Input math expression like 'eval +(1 2 3)' or 'eval *(8 3 2.5)' or 'quit' to exit";

    public HelpCommandLogic(HelpCommand command, CalculatorContext calculatorContext) {
        this.command = command;
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic() {
        calculatorContext.getWriter().write(help);
    }

}

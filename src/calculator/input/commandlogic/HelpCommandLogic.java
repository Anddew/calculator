package calculator.input.commandlogic;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.HelpCommand;

public class HelpCommandLogic implements ICommandLogic {

    private final static String HELP_MESSAGE = "Input math expression like 'eval +(1 2 3)' or 'eval *(8 3 2.5)' or 'quit' to exit";

    private CalculatorContext calculatorContext;
    private HelpCommand command;

    public HelpCommandLogic(HelpCommand command, CalculatorContext calculatorContext) {
        this.command = command;
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic() {
        calculatorContext.getWriter().write(HELP_MESSAGE);
    }

}

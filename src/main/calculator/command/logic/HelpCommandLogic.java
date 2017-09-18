package main.calculator.command.logic;

import main.calculator.CalculatorContext;
import main.calculator.command.HelpCommand;

public class HelpCommandLogic implements ICommandLogic {

    private final static String HELP_MESSAGE = "Input math expression like 'eval add(1 2 3)' or 'eval mul(8 3 2.5)' or 'quit' to exit";

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

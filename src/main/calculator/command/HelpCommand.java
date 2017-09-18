package main.calculator.command;

import main.calculator.CalculatorContext;

public class HelpCommand implements ICommand {

    public static String getName() {
        return "help";
    }

    private final static String HELP_MESSAGE = "Input math expression like 'eval add(1 2 3)' or 'eval mul(8 3 2.5)' or 'quit' to exit";

    private CalculatorContext calculatorContext;

    public HelpCommand(CalculatorContext calculatorContext) {
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void execute() {
        calculatorContext.getWriter().write(HELP_MESSAGE);
    }

}
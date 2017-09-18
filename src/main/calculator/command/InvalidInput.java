package main.calculator.command;

import main.calculator.CalculatorContext;

public class InvalidInput implements ICommand {

    private String comment;
    private CalculatorContext calculatorContext;

    public InvalidInput(String comment, CalculatorContext calculatorContext) {
        this.comment = comment;
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void execute() {
        calculatorContext.getWriter().write(comment);
    }

}

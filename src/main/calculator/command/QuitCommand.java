package main.calculator.command;

import main.calculator.CalculatorContext;

public class QuitCommand implements ICommand {

    public static String getName() {
        return "quit";
    }

    private CalculatorContext calculatorContext;

    public QuitCommand(CalculatorContext calculatorContext) {
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void execute() {
        calculatorContext.setQuitCondition();
    }

}

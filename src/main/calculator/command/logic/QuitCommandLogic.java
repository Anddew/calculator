package main.calculator.command.logic;

import main.calculator.CalculatorContext;
import main.calculator.command.QuitCommand;

public class QuitCommandLogic implements ICommandLogic {

    private CalculatorContext calculatorContext;
    private QuitCommand command;

    public QuitCommandLogic(QuitCommand command, CalculatorContext calculatorContext) {
        this.command = command;
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic() {
        calculatorContext.setQuitCondition();
    }
}

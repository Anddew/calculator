package calculator.input.commandlogic;

import calculator.ICalculatorContext;
import calculator.input.command.ICommand;

public class QuitCommandLogic implements ICommandLogic {

    private ICalculatorContext calculatorContext;

    public QuitCommandLogic(ICalculatorContext calculatorContext) {
        this.calculatorContext = calculatorContext;
    }

    @Override
    public void useLogic(ICommand command) {
        //NOP
    }
}

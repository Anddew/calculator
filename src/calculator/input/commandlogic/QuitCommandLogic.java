package calculator.input.commandlogic;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.command.QuitCommand;

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

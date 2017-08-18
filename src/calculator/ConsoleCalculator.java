package calculator;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.CommandHandlerFactory;
import calculator.input.command.ICommand;
import calculator.reader.ConsoleReader;
import calculator.reader.stringparsingtool.CommandIdentifier;
import calculator.writer.ConsoleWriter;

class ConsoleCalculator {

    private CommandHandlerFactory factory = new CommandHandlerFactory();
    private CalculatorContext calculatorContext = new CalculatorContext(new ConsoleWriter());
    private CommandIdentifier commandIdentifier = new CommandIdentifier(new ConsoleReader());


    void startConsoleCalculator() {
        while (!calculatorContext.isQuitCondition() ) {
            ICommand command = commandIdentifier.readCommand();
            factory.getLogic(command, calculatorContext).useLogic();
        }
    }
}
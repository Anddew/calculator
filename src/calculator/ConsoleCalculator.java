package calculator;

import calculator.calculatorcontext.CalculatorContext;
import calculator.input.CommandHandlerFactory;
import calculator.input.command.ICommand;
import calculator.reader.ConsoleCommandReader;
import calculator.writer.ConsoleWriter;

class ConsoleCalculator {

    private CommandHandlerFactory factory = new CommandHandlerFactory();
    private CalculatorContext calculatorContext = new CalculatorContext(new ConsoleWriter());
    private ConsoleCommandReader commandReader = new ConsoleCommandReader();

    void startConsoleCalculator() {
        while (!calculatorContext.isQuitCondition() ) {
            ICommand command = commandReader.readCommand();
            factory.getLogic(command, calculatorContext).useLogic();
        }
    }
}
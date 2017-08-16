package calculator;

import calculator.writer.IWriter;
import calculator.writer.ResultConsoleWriter;

public class ConsoleCalculatorContext implements ICalculatorContext {

    private IWriter writer = new ResultConsoleWriter();

    @Override
    public IWriter getWriter() {
        return writer;
    }

}

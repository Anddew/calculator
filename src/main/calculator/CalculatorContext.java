package main.calculator;

import main.calculator.output.IWriter;

public class CalculatorContext {

    private boolean quitCondition;
    private IWriter writer;

    public CalculatorContext(IWriter writer) {
        this.writer = writer;
    }

    public boolean isQuitCondition() {
        return quitCondition;
    }

    public void setQuitCondition() {
        this.quitCondition = true;
    }

    public IWriter getWriter() {
        return writer;
    }

}
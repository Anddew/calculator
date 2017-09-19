package main.calculator;

import main.calculator.command.creator.*;
import main.calculator.input.IReader;
import main.calculator.output.IWriter;

import java.util.HashMap;
import java.util.Map;

class Calculator {

    private CalculatorContext calculatorContext;
    private IReader reader;
    private IWriter writer;
    private CommandFactory commandFactory = new CommandFactory();

    private final Map<String, ICommandCreator> commands = new HashMap<>();

    public Calculator(IReader reader, IWriter writer) {
        this.calculatorContext = new CalculatorContext(writer);
        this.reader = reader;
        this.writer = writer;
    }

    void startCalculator() {
        while(!calculatorContext.isQuitCondition()) {
            String input = reader.read();
            if(input != null) {
                if(!input.isEmpty()) {
                    commandFactory.createCommand(input, calculatorContext).execute();
                }
            } else {
                calculatorContext.setQuitCondition();
            }
        }
        reader.close();
        writer.close();
    }

}
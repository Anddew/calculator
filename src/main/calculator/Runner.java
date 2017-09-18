package main.calculator;

import main.calculator.input.IReader;
import main.calculator.input.ReaderFactory;
import main.calculator.output.IWriter;
import main.calculator.output.WriterFactory;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        AppArgsValidator validator = new AppArgsValidator();
        ReaderFactory readerFactory = new ReaderFactory();
        WriterFactory writerFactory = new WriterFactory();

        if(validator.checkArguments(args)) {
            IReader reader = readerFactory.getReader(args);
            IWriter writer = writerFactory.getWriter(args);
            if(reader != null && writer != null) {
                new Calculator(reader, writer).startCalculator();
            }
        } else {
            System.out.println("Incorrect I/O arguments: " + Arrays.toString(args));
        }
    }
}

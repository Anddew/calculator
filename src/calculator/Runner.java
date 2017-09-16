package calculator;

import calculator.reader.IReader;
import calculator.reader.ReaderFactory;
import calculator.writer.IWriter;
import calculator.writer.WriterFactory;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        AppArgsValidator validator = new AppArgsValidator();
        if(validator.checkArguments(args)) {
            IReader reader = ReaderFactory.getReader(args);
            IWriter writer = WriterFactory.getWriter(args);
            if(reader != null && writer != null) {
                new Calculator(reader, writer).startCalculator();
            }
        } else {
            System.out.println("Incorrect I/O arguments: " + Arrays.toString(args));
        }
    }

}

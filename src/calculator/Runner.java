package calculator;

import calculator.reader.ConsoleReader;
import calculator.reader.FileReader;
import calculator.reader.IReader;
import calculator.writer.ConsoleWriter;
import calculator.writer.FileWriter;
import calculator.writer.IWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        String inputFilePath = args[0].substring(3);
        String outputFilePath = args[1].substring(3);
        IReader reader = null;
        IWriter writer = null;
        try {
            reader = new FileReader(inputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException. Can`t read file: " + inputFilePath);
        }
        if(reader == null) {
            reader = new ConsoleReader();
        }

        try {
            writer = new FileWriter(outputFilePath);
        }
        catch (IOException e) {
            System.out.println("IOException. Can`t get access to file: " + outputFilePath);
        }
        if(writer == null)
            writer = new ConsoleWriter();
        new Calculator(reader, writer).startCalculator();
    }
}

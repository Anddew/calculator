package calculator;

import calculator.reader.ConsoleReader;
import calculator.reader.FileReader;
import calculator.reader.IReader;
import calculator.writer.ConsoleWriter;
import calculator.writer.FileWriter;
import calculator.writer.IWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        String inputFilePath = null;
        String outputFilePath = null;
        boolean isValidArgs = true;

        for(String elem: args) {
            if(elem.startsWith("-i=") && inputFilePath == null) {
                inputFilePath = elem.substring(3);
            } else if(elem.startsWith("-o=") && outputFilePath == null) {
                outputFilePath = elem.substring(3);
            } else {
                System.out.println("Invalid I\\O arguments. (" + Arrays.toString(args) + ")");
                isValidArgs = false;
                break;
            }
        }

        if(isValidArgs) {
            try {
                IReader reader;
                IWriter writer;

                if(inputFilePath != null) {
                    reader = new FileReader(inputFilePath);
                } else {
                    reader = new ConsoleReader();
                }

                if(outputFilePath != null) {
                    writer = new FileWriter(outputFilePath);
                } else {
                    writer = new ConsoleWriter();
                }

                new Calculator(reader, writer).startCalculator();

            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException. Can`t read file: " + inputFilePath);
            } catch (IOException e) {
                System.out.println("IOException. Can`t get access to file: " + outputFilePath);
            }

        }
    }

}

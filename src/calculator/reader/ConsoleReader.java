package calculator.reader;

import java.util.Scanner;

public class ConsoleReader implements IReader {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        //NOP
    }
}
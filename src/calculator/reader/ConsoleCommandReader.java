package calculator.reader;

import calculator.input.command.ICommand;

import java.util.Scanner;

public class ConsoleCommandReader extends CommandReader {

    private Scanner scanner = new Scanner(System.in);

    public ICommand readCommand() {
        return super.readCommand(scanner.nextLine().trim());
    }

}

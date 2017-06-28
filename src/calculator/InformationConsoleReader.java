package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InformationConsoleReader {

    public String readLine() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public double readArgument() throws IOException {
        return Double.parseDouble(readLine());
    }


}

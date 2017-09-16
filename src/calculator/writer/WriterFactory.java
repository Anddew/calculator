package calculator.writer;

import java.io.IOException;
import java.util.Arrays;

public class WriterFactory {

    public IWriter getWriter(String[] args) {
        String outputArgument = Arrays.stream(args).filter(x -> x.startsWith("-o=")).findFirst().orElse(null);
        if(outputArgument != null) {
            try {
                return new FileWriter(outputArgument.substring(3));
            }  catch (IOException e) {
                System.out.println("IOException. Can`t get access to file: " + outputArgument);
                return null;
            }
        } else {
            return new ConsoleWriter();
        }
    }

}

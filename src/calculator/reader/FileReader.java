package calculator.reader;

import java.io.*;

public class FileReader implements IReader {

    private BufferedReader reader;

    FileReader(String filePath) throws FileNotFoundException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    }

    @Override
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Can't read from file.");
            return null;
        }
    }

}
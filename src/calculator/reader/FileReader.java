package calculator.reader;

import java.io.*;

public class FileReader implements IReader {

    private BufferedReader reader;

    public FileReader(String filePath) throws FileNotFoundException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    }

    @Override
    public String read() throws IOException {
        return reader.readLine();
    }

}
package calculator.writer;

import java.io.IOException;
import java.io.Writer;

class FileWriter implements IWriter {

    private Writer writer;

    FileWriter(String outputFilePath) throws IOException {
        writer = new java.io.FileWriter(outputFilePath);
    }

    @Override
    public void write(String result) {
        try {
            writer.write(result + "\r\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Can`t write to file.");
        }

    }

    @Override
    public void write(double result) {
        try {
            writer.write("" + result + "\r\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Can`t write.");
        }
    }

}

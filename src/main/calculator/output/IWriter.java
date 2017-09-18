package main.calculator.output;

public interface IWriter {

    void write(String result);

    void write(double result);

    void close();

}

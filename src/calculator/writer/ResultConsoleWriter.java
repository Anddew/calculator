package calculator.writer;

public class ResultConsoleWriter implements IWriter {

    @Override
    public void write(String result)  {
        System.out.println(result);
    }

    @Override
    public void write(double result) {
        System.out.println(result);
    }

}

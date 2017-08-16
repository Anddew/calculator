package calculator.operation;

class Division extends SequentialOperation {

    @Override
    public String getName() {
        return "div";
    }

    @Override
    public double accumulate(double accumulator, double value) {
        return accumulator / value;
    }

}
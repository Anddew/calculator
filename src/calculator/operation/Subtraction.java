package calculator.operation;

class Subtraction extends SequentialOperation {

    @Override
    public String getName() {
        return "sub";
    }

    @Override
    public double accumulate(double accumulator, double value) {
        return accumulator - value;
    }
}

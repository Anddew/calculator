package calculator.operation;

class Multiplication extends SequentialOperation {

    @Override
    public String getName() {
        return "mul";
    }

    @Override
    public double accumulate(double accumulator, double value) {
        return accumulator * value;
    }
}
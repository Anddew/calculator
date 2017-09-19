package main.calculator.operation;

class Addition extends SequentialOperation {

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public double accumulate(double accumulator, double value) {
        return accumulator + value;
    }

}
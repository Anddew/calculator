package calculator.input.stringparsingtool;

public class Bounds {

    private int lowerBound;
    private int upperBound;

    public Bounds(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    int getUpperBound() {
        return upperBound;
    }

    int getLowerBound() {
        return lowerBound;
    }

    boolean aboveUpperBound(int operandNumber) {
        return upperBound < operandNumber;
    }

    boolean isOutOfBounds(int operandNumber) {
        return (operandNumber < lowerBound) || (upperBound < operandNumber);
    }
}

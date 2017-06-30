package calculator.operations;

import java.util.Arrays;

public class Addition extends Operation {

    private char sign = '+';

    public char getSign() {
        return sign;
    }

    public double action(double[] argumentsArray) {
        double result = argumentsArray[0];
        for(int i = 1; i < argumentsArray.length; i++) {
            result += argumentsArray[i];
        }
        return result;
    }
}
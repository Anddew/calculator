package calculator.operation;


public class Division implements IOperation {

    public char getSign() {
        return '/';
    }

    public double action(double[] argumentsArray) {
        double result = argumentsArray[0];
        for(int i = 1; i < argumentsArray.length; i++) {
            result /= argumentsArray[i];
        }
        return result;
    }

}
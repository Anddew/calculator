package calculator;

public class AppArgsValidator {

    public static boolean checkArguments(String[] args) {
        int inputFilePathCount = 0;
        int outputFilePathCount = 0;

        for(String elem : args) {
            if(elem.startsWith("-i=")) {
                inputFilePathCount++;
            } else if(elem.startsWith("-o=")) {
                outputFilePathCount++;
            } else {
                return false;
            }
        }
        return (inputFilePathCount <= 1) && (outputFilePathCount <= 1);
    }

}
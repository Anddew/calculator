package calculator;

import java.util.Arrays;

class AppArgsValidator {

    boolean checkArguments(String[] args) {
        long inputPathsCount = Arrays.stream(args).filter(x -> x.startsWith("-i=")).count();
        long outputPathsCount = Arrays.stream(args).filter(x -> x.startsWith("-o=")).count();
        long othersCount = Arrays.stream(args).filter(x -> !x.startsWith("-i=") && !x.startsWith("-o=")).count();
        return (inputPathsCount <= 1) && (outputPathsCount <= 1) && (othersCount == 0);
    }

}
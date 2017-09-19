package main.calculator;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class AppArgsValidator {

    private static final int MAX_INPUT_STREAMS = 1;
    private static final int MAX_OUTPUT_STREAMS = 1;
    private static final int INDEFINITE_STREAMS = 0;

    boolean checkArguments(String[] args) {
        Map<Integer, Long> map = Arrays.stream(args).collect(
                Collectors.groupingBy(x -> {
                    switch (x.substring(0, 3)) {
                        case "-i=": return 0;
                        case "-o=": return 1;
                        default: return 2;
                    }
                }, Collectors.counting())
        );
        return (map.getOrDefault(0, 0L) <= MAX_INPUT_STREAMS)
                && map.getOrDefault(1, 0L) <= MAX_OUTPUT_STREAMS
                && map.getOrDefault(2, 0L) == INDEFINITE_STREAMS;
    }

}
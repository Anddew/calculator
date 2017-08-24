package calculator.reader.stringparsingtool;

import calculator.operation.IOperation;

public class Limiter {

    private int limit;
    private int currentSize;

    private Limiter(int limit) {
        this.limit = limit;
    }

    static Limiter createLimiter(IOperation operation) {
        switch (operation.getName()) {
            case "pow":case "log": return new Limiter(2);
            case "add":case "sub":case "mul":case "div": return new Limiter(20);
            default: throw new RuntimeException("Unknown operation in Limiter switch.");
        }
    }

    public static Limiter createLimiter() {
        return new Limiter(1);
    }

    public void increaseCurrentSize() {
        currentSize++;
    }

    public LimiterCompareResultType checkLimit() {
        System.out.println("currentSize = " + currentSize + '\n' + "limit = " + limit);
        if(currentSize == limit) {
            return LimiterCompareResultType.ON_LIMIT;
        } else if(currentSize > 0 && currentSize < limit) {
            return LimiterCompareResultType.IN_LIMIT;
        } else {
            return LimiterCompareResultType.OUT_OF_LIMIT;
        }
    }

}

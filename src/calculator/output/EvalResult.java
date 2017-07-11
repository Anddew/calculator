package calculator.output;

public class EvalResult implements IResult {

    private double result;

    public EvalResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    /*public ResultMarker getResultMarker() {
        return ResultMarker.EVAL_MARKER;
    }*/

    public String toString() {
        return ("Result - " + result);
    }

}
package calculator.results;

public abstract class ResultOperation {

    public ResultMarker resultMarker;

    public ResultOperation(ResultMarker resultMarker) {
        this.resultMarker = resultMarker;
    }

    public ResultMarker getResultMarker() {
        return resultMarker;
    }

}
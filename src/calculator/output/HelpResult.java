package calculator.output;

public class HelpResult implements IResult {

    public String toString() {
        return "Invalid input. Please, try again. Input math expression like +(1 2 3) or *(8 3 2.5) or 'quit' to exit";
    }

    /*public ResultMarker getResultMarker() {
        return ResultMarker.HELP_MARKER;
    }*/

}

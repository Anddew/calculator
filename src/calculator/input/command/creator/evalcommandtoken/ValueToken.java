package calculator.input.command.creator.evalcommandtoken;

public class ValueToken implements IEvalCommandToken {

    private double value;

    public ValueToken(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public EvalCommandTokenType getTokenType() {
        return EvalCommandTokenType.VALUE;
    }

}

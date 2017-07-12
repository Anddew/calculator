package calculator.input;

import calculator.operation.IOperation;

import java.util.List;

public class EvalCommand implements ICommand {

    private List<Double> argumentsList;
    private IOperation operation;

    public EvalCommand(List<Double> argumentsList, IOperation operation) {
        this.argumentsList = argumentsList;
        this.operation = operation;
    }

    public List<Double> getArgumentsList() {
        return argumentsList;
    }

    public IOperation getOperation() {
        return operation;
    }

    public CommandMarker getCommandMarker() {
        return CommandMarker.EVAL_MARKER;
    }

}

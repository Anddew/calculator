package calculator.operation;

import calculator.input.stringparsingtool.Bounds;

import java.util.List;

public interface IOperation {

    String getName();

    Bounds getOperandsBounds();

    double apply(List<Double> argumentsList);

}
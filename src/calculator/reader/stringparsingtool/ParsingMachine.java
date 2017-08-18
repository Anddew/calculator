package calculator.reader.stringparsingtool;

import calculator.input.command.InvalidInput;
import calculator.input.command.evalcommandtoken.OperationToken;
import calculator.input.command.evalcommandtoken.OperationEndToken;
import calculator.input.command.evalcommandtoken.ValueToken;
import calculator.operation.SequentialOperation;
import calculator.operation.OperationsMap;

import java.util.*;

public class ParsingMachine extends FSM<ReaderState, ReaderAccumulator, Character> {

    private Map<ReaderState, List<ConditionAndAction>> stateTransitionMap = new HashMap<>();

    public ParsingMachine(ReaderState readerState, ReaderAccumulator readerAccumulator) {
        super(readerState, readerAccumulator);
        stateTransitionMap.put(ReaderState.READ_OPERATION, Arrays.asList(
            new ConditionAndAction(
                Character::isLetter,
                input -> {
                    getAccumulator().getBuffer().append(input);
                    return getState();
                }
            ),
            new ConditionAndAction(
                input -> input == '(',
                input -> {
                    String operationName = getAccumulator().getBuffer().toString();
                    SequentialOperation operation = OperationsMap.operations.get(operationName);
                    if(operation != null) {
                        getAccumulator().getElementsList().add(new OperationToken(operation));
                        getAccumulator().getBuffer().setLength(0);
                        return ReaderState.READ_OPERATION_OR_VALUE;
                    } else {
                        getAccumulator().setCommand(new InvalidInput("Invalid input. Unknown operation." + getAccumulator().getBuffer().toString()));
                        return ReaderState.QUIT_STATE;
                    }
                }
            ),
            new ConditionAndAction(
                input -> true,
                input -> {
                    getAccumulator().setCommand(new InvalidInput("Invalid input. Invalid symbol after operation."));
                    return ReaderState.QUIT_STATE;
                }
            )
        ));

        stateTransitionMap.put(ReaderState.READ_OPERATION_OR_VALUE, Arrays.asList(
                new ConditionAndAction(
                        input -> input == ' ',
                        input -> getState()
                ),
                new ConditionAndAction(
                        input -> input == ')',
                        input -> {
                            getAccumulator().getElementsList().add(new OperationEndToken());
                            return getState();
                        }
                ),
                new ConditionAndAction(
                        Character::isLetter,
                        input -> {
                            getAccumulator().getBuffer().append(input);
                            return ReaderState.READ_OPERATION;
                        }
                ),
                new ConditionAndAction(
                        Character::isDigit,
                        input -> {
                            getAccumulator().getBuffer().append(input);
                            return ReaderState.READ_VALUE;
                        }
                ),
                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Invalid symbol."));
                            return ReaderState.QUIT_STATE;
                        }
                )
        ));

        stateTransitionMap.put(ReaderState.READ_VALUE, Arrays.asList(
                new ConditionAndAction(
                        Character::isDigit,
                        input -> {
                            getAccumulator().getBuffer().append(input);
                            return ReaderState.READ_VALUE;
                        }
                ),
                new ConditionAndAction(
                        input -> input == '.',
                        input -> {
                            if (! getAccumulator().getBuffer().toString().contains(".")) {
                                getAccumulator().getBuffer().append(input);
                                return ReaderState.READ_VALUE;
                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Value has dot symbol several times."));
                                return ReaderState.QUIT_STATE;
                            }
                        }
                ),
                new ConditionAndAction(
                        input -> input == ' ',
                        input -> {
                            if (!getAccumulator().getBuffer().toString().endsWith(".")) {
                                getAccumulator().getElementsList().add(
                                        new ValueToken(
                                                Double.parseDouble(getAccumulator().getBuffer().toString())
                                        )
                                );
                                getAccumulator().getBuffer().setLength(0);
                                return ReaderState.READ_OPERATION_OR_VALUE;
                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Symbol '.' can not be at the end of value." + getAccumulator().getBuffer().toString()));
                                return ReaderState.QUIT_STATE;
                            }
                        }
                ),
                new ConditionAndAction(
                        input -> input == ')',
                        input -> {
                            getAccumulator().getElementsList().add(
                                    new ValueToken(
                                            Double.parseDouble(getAccumulator().getBuffer().toString())
                                    ));
                            getAccumulator().getBuffer().setLength(0);
                            getAccumulator().getElementsList().add(new OperationEndToken());
                            return ReaderState.READ_OPERATION_OR_VALUE;
                        }
                ),
                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Invalid symbol."));
                            return ReaderState.QUIT_STATE;
                        }
                )
        ));
    }

    @Override
    public Map<ReaderState, List<ConditionAndAction>> getStateTransitionMap() {
        return stateTransitionMap;
    }

    @Override
    public boolean isFinalState(ReaderState state) {
        return state.equals(ReaderState.QUIT_STATE);
    }

}

package calculator.reader.stringparsingtool;

import calculator.input.command.EvalCommand;
import calculator.input.command.InvalidInput;
import calculator.input.command.evalcommandtoken.OperationToken;
import calculator.input.command.evalcommandtoken.OperationEndToken;
import calculator.input.command.evalcommandtoken.ValueToken;
import calculator.operation.IOperation;
import calculator.operation.OperationsMap;

import java.util.*;

public class ParsingMachine extends FSM<ReaderStateType, ReaderAccumulator, Character> {

    private Map<ReaderStateType, List<ConditionAndAction>> stateTransitionMap = new HashMap<>();

    public ParsingMachine(ReaderStateType readerState, ReaderAccumulator readerAccumulator) {
        super(readerState, readerAccumulator);

        stateTransitionMap.put(ReaderStateType.READING, Arrays.asList(
                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Unexpected end of input."));
                            return ReaderStateType.FAILURE;
                        }
                ),

                new ConditionAndAction(
                        input -> input == ' ',
                        input -> getState()
                ),

                new ConditionAndAction(
                        input -> input == ')',
                        input -> {
                            getAccumulator().getElementsList().add(new OperationEndToken());
                            getAccumulator().getStack().pop();
                            switch (getAccumulator().getStack().peek().checkLimit()) {
                                case IN_LIMIT: case ON_LIMIT: return getState();
                                case OUT_OF_LIMIT:
                                    getAccumulator().setComment("Invalid input. Out of limit.");
                                    return ReaderStateType.FAILURE;
                                default: throw new RuntimeException("Unknown token for READ_VALUE_STATE.");
                            }
                        }
                ),
                new ConditionAndAction(
                        Character::isLetter,
                        input -> {
                            getAccumulator().getBuffer().append(input);
                            getAccumulator().getStack().peek().increaseCurrentSize();
                            return ReaderStateType.READING_OPERATION;
                        }
                ),
                new ConditionAndAction(
                        Character::isDigit,
                        input -> {
                            getAccumulator().getBuffer().append(input);
                            getAccumulator().getStack().peek().increaseCurrentSize();
                            return ReaderStateType.READING_VALUE;
                        }
                ),
                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setComment("Invalid input. Invalid symbol.");
                            return ReaderStateType.FAILURE;
                        }
                )
        ));

        stateTransitionMap.put(ReaderStateType.READING_OPERATION, Arrays.asList(
                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Unexpected end of input."));
                            return ReaderStateType.FAILURE;
                        }
                ),
                new ConditionAndAction(
                        Character::isLetter,
                        input -> {
                            getAccumulator().getBuffer().append(input);
                            return getState();
                        }
                ),
                new ConditionAndAction(
                        Character::isDigit,
                        input -> {
                            getAccumulator().getBuffer().append(input);
                            return getState();
                        }
                ),
                new ConditionAndAction(
                        input -> input == '(',
                        input -> {
                            String operationName = getAccumulator().getBuffer().toString();
                            IOperation operation = OperationsMap.operations.get(operationName);
                            if(operation != null) {
                                switch (getAccumulator().getStack().peek().checkLimit()) {
                                    case IN_LIMIT: case ON_LIMIT:
                                        getAccumulator().getElementsList().add(new OperationToken(operation));
                                        getAccumulator().getStack().push(Limiter.createLimiter(operation));
                                        getAccumulator().getBuffer().setLength(0);
                                        return ReaderStateType.READING;
                                    default:
                                        getAccumulator().setComment("Invalid input. Number of arguments is out of limit.");
                                        return ReaderStateType.FAILURE;
                                }
                            } else {
                                getAccumulator().setComment("Invalid input. Unknown operation." + getAccumulator().getBuffer().toString());
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),
                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setComment("Invalid input. Invalid symbol after operation.");
                            return ReaderStateType.FAILURE;
                        }
                )
        ));

        stateTransitionMap.put(ReaderStateType.READING_VALUE, Arrays.asList(
                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Unexpected end of input."));
                            return ReaderStateType.FAILURE;
                        }
                ),
                new ConditionAndAction(
                        Character::isDigit,
                        input -> {
                            getAccumulator().getBuffer().append(input);
                            return ReaderStateType.READING_VALUE;
                        }
                ),
                new ConditionAndAction(
                        input -> input == '.',
                        input -> {
                            if (! getAccumulator().getBuffer().toString().contains(".")) {
                                getAccumulator().getBuffer().append(input);
                                return ReaderStateType.READING_VALUE;
                            } else {
                                getAccumulator().setComment("Invalid input. Value has dot symbol several times.");
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),
                new ConditionAndAction(
                        input -> input == ' ',
                        input -> {
                            if (!getAccumulator().getBuffer().toString().endsWith(".")) {
                                switch (getAccumulator().getStack().peek().checkLimit()) {
                                    case IN_LIMIT: case ON_LIMIT:
                                            getAccumulator().getElementsList().add(
                                                new ValueToken(
                                                        Double.parseDouble(getAccumulator().getBuffer().toString())
                                                )
                                        );
                                        getAccumulator().getBuffer().setLength(0);
                                        return ReaderStateType.READING;
                                    case OUT_OF_LIMIT:
                                        getAccumulator().setComment("Invalid input. Number of arguments is out of limit.");
                                        return ReaderStateType.FAILURE;
                                    default: throw new RuntimeException("Unknown token for READ_VALUE_STATE.");
                                }
                            } else {
                                getAccumulator().setComment("Invalid input. Symbol '.' can not be at the end of value." + getAccumulator().getBuffer().toString());
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),
                new ConditionAndAction(
                        input -> input == ')',
                        input -> {
                            if (!getAccumulator().getBuffer().toString().endsWith(".")) {
                                switch (getAccumulator().getStack().peek().checkLimit()) {
                                    case IN_LIMIT:
                                    case ON_LIMIT:
                                        getAccumulator().getElementsList().add(
                                                new ValueToken(
                                                        Double.parseDouble(getAccumulator().getBuffer().toString())
                                                ));
                                        getAccumulator().getBuffer().setLength(0);
                                        getAccumulator().getElementsList().add(new OperationEndToken());
                                        getAccumulator().getStack().pop();
                                        switch (getAccumulator().getStack().peek().checkLimit()) {
                                            case IN_LIMIT: return ReaderStateType.WAITING_FOR_SPACE;
                                            case ON_LIMIT:
                                                return ReaderStateType.FINISHING;
                                            case OUT_OF_LIMIT:
                                                getAccumulator().setComment("Invalid input. Number of arguments is out of limit.");
                                                return ReaderStateType.FAILURE;
                                        }
                                        return ReaderStateType.WAITING_FOR_SPACE;
                                    case OUT_OF_LIMIT:
                                        getAccumulator().setComment("Invalid input. Number of arguments is out of limit.");
                                        return ReaderStateType.FAILURE;
                                    default:
                                        throw new RuntimeException("Unknown token for READ_VALUE_STATE.");
                                }
                            } else {
                                getAccumulator().setComment("Invalid input. Symbol '.' can not be at the end of value." + getAccumulator().getBuffer().toString());
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),
                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setComment("Invalid input. Invalid symbol.");
                            return ReaderStateType.FAILURE;
                        }
                )
        ));

        stateTransitionMap.put(ReaderStateType.WAITING_FOR_SPACE, Arrays.asList(
                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            getAccumulator().setComment("Invalid input. Unexpected end of input.");
                            return ReaderStateType.FAILURE;
                        }
                ),
                new ConditionAndAction(
                        input -> input == ' ',
                        input -> ReaderStateType.READING
                ),
                new ConditionAndAction(
                        input -> input == ')',
                        input -> {
                            getAccumulator().getElementsList().add(new OperationEndToken());
                            getAccumulator().getStack().pop();
                            if(getAccumulator().getStack().empty()) {
                                return ReaderStateType.INTERRUPT;
                            } else {
                                return getState();
                            }
                        }
                ),
                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setComment("Invalid input. Invalid symbol.");
                            return ReaderStateType.FAILURE;
                        }
                )
        ));

        stateTransitionMap.put(ReaderStateType.FAILURE, Arrays.asList(
                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setCommand(new InvalidInput(getAccumulator().getComment()));
                            return ReaderStateType.INTERRUPT;
                        }
                )
        ));

        stateTransitionMap.put(ReaderStateType.FINISHING, Arrays.asList(
                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            getAccumulator().setCommand(new EvalCommand(getAccumulator().getElementsList()));
                            return getState();
                        }
                ),
                new ConditionAndAction(
                        input -> input == ' ',
                        input -> ReaderStateType.FINISHING
                ),

                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setComment("Invalid input. Invalid symbol.");
                            return ReaderStateType.INTERRUPT;
                        }
                )
        ));
    }

    @Override
    public Map<ReaderStateType, List<ConditionAndAction>> getStateTransitionMap() {
        return stateTransitionMap;
    }

    @Override
    public boolean isFinalState(ReaderStateType state) {
        return state.equals(ReaderStateType.INTERRUPT);
    }

}

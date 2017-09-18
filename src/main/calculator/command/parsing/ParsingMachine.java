package main.calculator.command.parsing;

import main.calculator.command.EvalCommand;
import main.calculator.command.InvalidInput;
import main.calculator.command.creator.evalcommandtoken.OperationEndToken;
import main.calculator.command.creator.evalcommandtoken.OperationToken;
import main.calculator.command.creator.evalcommandtoken.ValueToken;
import main.calculator.operation.IOperation;
import main.calculator.operation.OperationsMap;

import java.util.*;

public class ParsingMachine extends FSM<ReaderStateType, ReaderAccumulator, Character> {

    private Map<ReaderStateType, List<ConditionAndAction>> stateTransitionMap = new HashMap<>();
    private int inputsCounter;

    public ParsingMachine(ReaderStateType readerState, ReaderAccumulator readerAccumulator) {
        super(readerState, readerAccumulator);
        inputsCounter = readerAccumulator.getPrefixLength();

        stateTransitionMap.put(ReaderStateType.READING, Arrays.asList(
                new ConditionAndAction(
                        input -> (Character.isLetter(input) && Character.isLowerCase(input)) || Character.isDigit(input) || input == '-',
                        input -> {
                            getAccumulator().getOperandCounterStack().peek().increment();
                            if(!getAccumulator().getBoundsStack().peek().aboveUpperBound(
                                    getAccumulator().getOperandCounterStack().peek().getCount())
                                    ) {
                                getAccumulator().getBuffer().append(input);
                                if(Character.isLetter(input)) {
                                    return ReaderStateType.READING_OPERATION;
                                } else {
                                    return ReaderStateType.READING_VALUE;
                                }
                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Number of operands is above upper bound. Max number of operands - " + getAccumulator().getBoundsStack().peek().getUpperBound() + "."));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        Character::isWhitespace,
                        input -> getState()
                ),

                new ConditionAndAction(
                        input -> input == ')',
                        input -> {
                            if(!getAccumulator().getBoundsStack().peek().isOutOfBounds(
                                            getAccumulator().getOperandCounterStack().peek().getCount())
                                    ) {
                                getAccumulator().getBoundsStack().pop();
                                getAccumulator().getOperandCounterStack().pop();
                                if (getAccumulator().numberEndTokenExpected >= 0) {
                                    getAccumulator().getElementsList().add(new OperationEndToken());
                                    return ReaderStateType.WAITING_FOR_END_SYMBOL;
                                } else {
                                    getAccumulator().setCommand(new InvalidInput("Invalid input. Unbalanced number of end token reached. ('" + input + "':" + inputsCounter + ")"));
                                    return ReaderStateType.FAILURE;
                                }
                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Number of operands is above upper bound. Number of operand must be inside the bounds - " + getAccumulator().getBoundsStack().peek().getLowerBound() + " and " + getAccumulator().getBoundsStack().peek().getUpperBound() + "."));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            if(!getAccumulator().getBoundsStack().peek().isOutOfBounds(
                                    getAccumulator().getOperandCounterStack().peek().getCount()
                            )) {
                                getAccumulator().setCommand(new EvalCommand(getAccumulator().getElementsList()));
                                return ReaderStateType.FINISHING;
                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Number of operands is above upper bound. Number of operand must be inside the bounds - " + getAccumulator().getBoundsStack().peek().getLowerBound() + " and " + getAccumulator().getBoundsStack().peek().getUpperBound() + "."));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Illegal character. ('" + input + "':" + inputsCounter +")"));
                            return ReaderStateType.FAILURE;
                        }
                )

        ));

        stateTransitionMap.put(ReaderStateType.READING_OPERATION, Arrays.asList(
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
                                if(!getAccumulator().getBoundsStack().peek().isOutOfBounds(
                                                getAccumulator().getOperandCounterStack().peek().getCount())
                                    ) {
                                    getAccumulator().getBoundsStack().push(operation.getOperandsBounds());
                                    getAccumulator().getOperandCounterStack().push(new OperandCounter());
                                    getAccumulator().numberEndTokenExpected++;
                                    getAccumulator().getElementsList().add(new OperationToken(operation));
                                    getAccumulator().getBuffer().setLength(0);
                                    return ReaderStateType.READING;
                                } else {
                                    getAccumulator().setCommand(new InvalidInput("Invalid input. Number of operands is above upper bound. Number of operand must be inside the bounds - " + getAccumulator().getBoundsStack().peek().getLowerBound() + " and " + getAccumulator().getBoundsStack().peek().getUpperBound() + "."));
                                    return ReaderStateType.FAILURE;
                                }
                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Unknown function name. ('" + getAccumulator().getBuffer().toString() +"':" + (inputsCounter - getAccumulator().getBuffer().toString().length()) + ")"));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Expression has wrong format."));
                            return ReaderStateType.FAILURE;
                        }
                ),

                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Illegal character. ('" + input + "':" + inputsCounter +")"));
                            return ReaderStateType.FAILURE;
                        }
                )
        ));

        stateTransitionMap.put(ReaderStateType.READING_VALUE, Arrays.asList(
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
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Value has dot symbol several times."));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        Character::isWhitespace,
                        input -> {
                            if (!getAccumulator().getBuffer().toString().endsWith(".")) {
                                if(!getAccumulator().getBoundsStack().peek().aboveUpperBound(
                                        getAccumulator().getOperandCounterStack().peek().getCount())
                                ) {
                                    getAccumulator().getElementsList().add(
                                            new ValueToken(
                                                    Double.parseDouble(getAccumulator().getBuffer().toString())
                                            )
                                    );
                                    getAccumulator().getBuffer().setLength(0);
                                    return ReaderStateType.READING;
                                } else {
                                    getAccumulator().setCommand(new InvalidInput("Invalid input. Number of operands is above upper bound. Max number of operands - " + getAccumulator().getBoundsStack().peek().getUpperBound() + "."));
                                    return ReaderStateType.FAILURE;
                                }

                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Symbol '.' can not be at the end of value." + getAccumulator().getBuffer().toString()));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        input -> input == ')',
                        input -> {
                            if (!getAccumulator().getBuffer().toString().endsWith(".")) {
                                if(!getAccumulator().getBoundsStack().peek().isOutOfBounds(
                                        getAccumulator().getOperandCounterStack().peek().getCount())
                                        ) {
                                    getAccumulator().getElementsList().add(
                                            new ValueToken(
                                                    Double.parseDouble(getAccumulator().getBuffer().toString())
                                            )
                                    );
                                    getAccumulator().getBuffer().setLength(0);
                                    getAccumulator().getElementsList().add(new OperationEndToken());
                                    getAccumulator().numberEndTokenExpected--;
                                    if(getAccumulator().numberEndTokenExpected >= 0) {
                                        return ReaderStateType.WAITING_FOR_END_SYMBOL;
                                    } else {
                                        getAccumulator().setCommand(new InvalidInput("Invalid input. Unbalanced number of end token reached. ('" + input + "':" + inputsCounter + ")"));
                                        return ReaderStateType.FAILURE;
                                    }

                                } else {
                                    getAccumulator().setCommand(new InvalidInput("Invalid input. Number of operands is above upper bound. Number of operand must be inside the bounds - " + getAccumulator().getBoundsStack().peek().getLowerBound() + " and " + getAccumulator().getBoundsStack().peek().getUpperBound() + "."));
                                    return ReaderStateType.FAILURE;
                                }

                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Symbol '.' can not be at the end of value." + getAccumulator().getBuffer().toString()));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            if(getAccumulator().numberEndTokenExpected == 0 && getAccumulator().getBoundsStack().peek().isOutOfBounds(getAccumulator().getOperandCounterStack().peek().getCount())) {
                                getAccumulator().getElementsList().add(
                                        new ValueToken(
                                                Double.parseDouble(getAccumulator().getBuffer().toString())
                                        )
                                );
                                getAccumulator().setCommand(new EvalCommand(getAccumulator().getElementsList()));
                                return ReaderStateType.FINISHING;
                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Unexpected end of input."));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Illegal character. ('" + input + "':" + inputsCounter +")"));
                            return ReaderStateType.FAILURE;
                        }
                )
        ));

        stateTransitionMap.put(ReaderStateType.WAITING_FOR_END_SYMBOL, Arrays.asList(
                new ConditionAndAction(
                        input -> input == '\n',
                        input -> {
                            getAccumulator().setCommand(new EvalCommand(getAccumulator().getElementsList()));
                            return ReaderStateType.FINISHING;
                        }
                ),

                new ConditionAndAction(
                        Character::isWhitespace,
                        input -> ReaderStateType.READING
                ),

                new ConditionAndAction(
                        input -> input == ')',
                        input -> {
                            getAccumulator().numberEndTokenExpected--;
                            if(getAccumulator().numberEndTokenExpected >= 0) {
                                getAccumulator().getElementsList().add(new OperationEndToken());
                                return ReaderStateType.WAITING_FOR_END_SYMBOL;
                            } else {
                                getAccumulator().setCommand(new InvalidInput("Invalid input. Unbalanced number of end token reached. ('" + input + "':" + inputsCounter + ")"));
                                return ReaderStateType.FAILURE;
                            }
                        }
                ),

                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Invalid symbol. ('" + input + "':" + inputsCounter +")"));
                            return ReaderStateType.FAILURE;
                        }
                )
        ));

        stateTransitionMap.put(ReaderStateType.FINISHING, Arrays.asList(
                new ConditionAndAction(
                        Character::isWhitespace,
                        input -> ReaderStateType.FINISHING
                ),

                new ConditionAndAction(
                        input -> true,
                        input -> {
                            getAccumulator().setCommand(new InvalidInput("Invalid input. Illegal character. ('" + input + "':" + inputsCounter +")"));
                            return ReaderStateType.FAILURE;
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
        return state.equals(ReaderStateType.FAILURE);
    }

    @Override
    public void everyInputAction() {
        inputsCounter++;
    }

}

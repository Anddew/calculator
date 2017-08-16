package calculator.reader.stringparsingtool;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class FSM<State, Accumulator, Input> {
    class ConditionAndAction {
        private Function<Input, Boolean> condition;
        private Function<Input, State> action;

        ConditionAndAction(Function<Input, Boolean> condition, Function<Input, State> action) {
            this.condition = condition;
            this.action = action;
        }

        Function<Input, Boolean> getCondition() {
            return condition;
        }

        Function<Input, State> getAction() {
            return action;
        }
    }

    private State state;
    private Accumulator accumulator;

    FSM(State state, Accumulator accumulator) {
        this.state = state;
        this.accumulator = accumulator;
    }

    State getState() {
        return state;
    }

    public Accumulator getAccumulator() {
        return accumulator;
    }

    public abstract Map<State, List<ConditionAndAction>> getStateTransitionMap();

    public abstract boolean isFinalState(State state);

    public void handle(List<Input> inputs) {
        for (Input input : inputs) {
            State newState = null;
            for (ConditionAndAction conditionAndAction : getStateTransitionMap().get(state)) {
                if (conditionAndAction.getCondition().apply(input)) {
                    newState = conditionAndAction.getAction().apply(input);
                    break;
                }
            }
            if (isFinalState(newState)) {
                break;
            }
            if (newState != null) {
                //System.out.println("For input " + input + " changing state from " + state + " to " + newState);
                state = newState;
            } else {
                throw new RuntimeException("Proper action in state " + state + " for input " + input + " wasn't found");
            }
        }
    }
}

package calculator.input.stringparsingtool;

public enum ReaderStateType {

    READING,
    READING_OPERATION,
    READING_VALUE,
    WAITING_FOR_END_SYMBOL,
    FAILURE,
    FINISHING,

}

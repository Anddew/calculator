package calculator.reader.stringparsingtool;

public enum ReaderStateType {

    READING,
    READING_OPERATION,
    READING_VALUE,
    WAITING_FOR_SPACE,
    FAILURE,
    FINISHING,
    INTERRUPT

}
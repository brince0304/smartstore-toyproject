package Exceptions;

public class InputTypeMismatchException extends NumberFormatException{
    public InputTypeMismatchException() {
        super("입력 형식이 잘못되었습니다.");
    }
}

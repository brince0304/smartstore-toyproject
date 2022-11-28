package Utils;

public class InputTypeMismatchException extends NumberFormatException{
    public InputTypeMismatchException() {
        super("잘못된 입력이거나 실행입니다.");
    }
}

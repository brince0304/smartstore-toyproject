package Exceptions;

public class UnknownException extends Exception{
    public UnknownException() {
        super("알 수 없는 오류가 발생했습니다.");
    }
}


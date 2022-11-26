package Exceptions;

public class NoCustomersException extends Exception{
    public NoCustomersException() {
        super("등록된 고객이 존재하지 않습니다. 고객을 먼저 등록해주세요.");
    }
}


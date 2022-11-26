package Exceptions;

public class SameCustomerIdException extends Exception{
    public SameCustomerIdException() {
        super("같은 아이디가 존재합니다.");
    }
}


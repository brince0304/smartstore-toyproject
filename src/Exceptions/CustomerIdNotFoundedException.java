package Exceptions;

public class CustomerIdNotFoundedException extends Exception{
    public CustomerIdNotFoundedException() {
        super("존재하지 않는 아이디입니다.");
    }
}


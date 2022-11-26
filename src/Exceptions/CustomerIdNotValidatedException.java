package Exceptions;

public class CustomerIdNotValidatedException extends Exception{
    public CustomerIdNotValidatedException() {
        super("아이디는 5자 이상 10자 이하로 입력해주세요.");
    }

}

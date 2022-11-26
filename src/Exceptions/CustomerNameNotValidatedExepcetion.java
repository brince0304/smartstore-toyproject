package Exceptions;

public class CustomerNameNotValidatedExepcetion extends Exception{
    public CustomerNameNotValidatedExepcetion() {
        super("이름은 2자 이상 5자 이하로 입력해주세요.");
    }
}

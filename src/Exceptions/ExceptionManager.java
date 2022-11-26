package Exceptions;

public class ExceptionManager {

    public static void catchSameCustomerIdException() {
        try {
            throw new SameCustomerIdException();
        } catch (SameCustomerIdException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void catchInputTypeMismatchException() {
        try {
            throw new InputTypeMismatchException();
        } catch (InputTypeMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void catchNoCustomersException() {
        try {
            throw new NoCustomersException();
        } catch (NoCustomersException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void catchNullpointerException() {
        try {
            throw new NullpointerException();
        } catch (NullpointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void catchException(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void catchUnknownException() {
        try {
            throw new UnknownException();
        } catch (UnknownException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void catchCustomerIdNotValidatedException() {
        try {
            throw new CustomerIdNotValidatedException();
        } catch (CustomerIdNotValidatedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void catchCustomerNameNotValidatedException() {
        try {
            throw new CustomerNameNotValidatedExepcetion();
        } catch (CustomerNameNotValidatedExepcetion e) {
            System.out.println(e.getMessage());
        }
    }

    public static void catchCustomerIdNotFoundedException() {
        try {
            throw new CustomerIdNotFoundedException();
        } catch (CustomerIdNotFoundedException e) {
            System.out.println(e.getMessage());
        }
    }

}

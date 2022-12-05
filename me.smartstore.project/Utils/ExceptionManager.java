package Utils;

public class ExceptionManager {




    public static void catchInputTypeMismatchException() {
        try {
            throw new InputTypeMismatchException();
        } catch (InputTypeMismatchException e) {
            System.out.println(e.getMessage());
        }
    }




}

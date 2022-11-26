import Exceptions.SameCustomerIdException;
import Menu.MenuImpl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SameCustomerIdException {
          SmartStoreApplication.getInstance().test();
            SmartStoreApplication.getInstance().run();
    }
}

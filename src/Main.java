import Customer.ClassifiedCustomers;
import Customer.Customers;
import Menu.CustomerMenu;
import Menu.Menu;
import Menu.MenuImpl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MenuImpl menu = new MenuImpl();
        System.out.println("고객 관리 프로그램");
        while(true){
            try {
                menu.selectMenu(menu.showMenu());

            }
            catch (NumberFormatException e){
                System.out.println("잘못된 입력입니다.");
            }
        }

    }
}

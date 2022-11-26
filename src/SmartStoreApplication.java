import Customer.Customer;
import Customer.Customers;
import Exceptions.SameCustomerIdException;
import Group.Groups;
import Group.Parameter;
import Menu.MenuImpl;

import java.io.IOException;

public class SmartStoreApplication {
    static SmartStoreApplication smartStoreApplication;
    private final MenuImpl menu;

    private final Groups groupsTest;
    private final Customers customersTest;

    private SmartStoreApplication(MenuImpl menu, Groups groupsTest, Customers customersTest) {
        this.menu = menu;
        this.groupsTest = groupsTest;
        this.customersTest = customersTest;
    }

    public static SmartStoreApplication getInstance() {
        if (smartStoreApplication == null) {
            smartStoreApplication = new SmartStoreApplication(new MenuImpl(), Groups.getInstance(), Customers.getInstance());
        }
        return smartStoreApplication;
    }

    public void run() throws IOException, IOException, SameCustomerIdException {
        while (true) {
            int menu = this.menu.showMenu();
            this.menu.selectMenu(menu);
        }
    }

    public void test() throws IOException, SameCustomerIdException {
        groupsTest.addGroup(1, Parameter.of(10,10));
        groupsTest.addGroup(2, Parameter.of(20,20));
        groupsTest.addGroup(3, Parameter.of(30,30));

        for(int i=0; i<20;i++){
            customersTest.addCustomer(Customer.of(String.valueOf(i),String.valueOf(i),i*2,i*3));
        }
    }
}

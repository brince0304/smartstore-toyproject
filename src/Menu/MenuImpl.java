package Menu;

import Customer.ClassifiedCustomers;
import Customer.Customers;

import java.io.IOException;

public class MenuImpl implements  Menu{


    private static MenuImpl instance;


    public MenuImpl() {
    }

    @Override
    public int showMenu() throws IOException {
        System.out.println("메뉴를 선택하세요");
        System.out.println("1. 고객 관리");
        System.out.println("2. 그룹 관리");
        System.out.println("3. 요약조회");
        System.out.println("4. 종료");
        return Integer.parseInt(inputString());
    }
    @Override
    public void selectMenu(int menu) throws IOException {

        switch (menu){
            case 1:
                CustomerMenu customerMenu = CustomerMenu.getInstance(Customers.getInstance(), ClassifiedCustomers.getInstance());
                customerMenu.selectMenu(customerMenu.showMenu());
                break;
            case 2:
                GroupMenu groupMenu = GroupMenu.getInstance(Customers.getInstance(), ClassifiedCustomers.getInstance());
                groupMenu.selectMenu(groupMenu.showMenu());
                break;
            case 3:
                SummaryMenu summaryMenu = SummaryMenu.getInstance(Customers.getInstance(), ClassifiedCustomers.getInstance());
                summaryMenu.selectMenu(summaryMenu.showMenu());
                break;
            case 4:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
}

package Menu;

import Customer.Customers;
import Exceptions.ExceptionManager;
import Exceptions.InputTypeMismatchException;
import Exceptions.SameCustomerIdException;
import Group.Groups;

import java.io.IOException;

public class MenuImpl implements  Menu{

    public MenuImpl() {
    }

    @Override
    public int showMenu() throws IOException , InputTypeMismatchException {
        try {
            System.out.println("메뉴를 선택하세요");
            System.out.println("1. 고객 관리");
            System.out.println("2. 그룹 관리");
            System.out.println("3. 요약조회");
            System.out.println("4. 종료");
            return Integer.parseInt(inputString());
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
            return 0;
        }
    }
    @Override
    public void selectMenu(int menu) throws IOException, SameCustomerIdException {

        switch (menu){
            case 1:
                CustomerMenu customerMenu = CustomerMenu.getInstance();
                customerMenu.selectMenu(customerMenu.showMenu());
                break;
            case 2:
                GroupMenu groupMenu = GroupMenu.getInstance();
                groupMenu.selectMenu(groupMenu.showMenu());
                break;
            case 3:
                SummaryMenu summaryMenu = SummaryMenu.getInstance();
                summaryMenu.selectMenu(summaryMenu.showMenu());
                break;
            case 4:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
                break;
            default:
                ExceptionManager.catchInputTypeMismatchException();
                break;
        }
    }
}

package Menu;

import Utils.ExceptionManager;
import Utils.InputTypeMismatchException;

import java.io.IOException;

public class MenuImpl implements  Menu{
    static MenuImpl instance;
    private final CustomerMenu customerMenu;
    private final GroupMenu groupMenu;
    private final SummaryMenu summaryMenu;

    private MenuImpl(CustomerMenu customerMenu, GroupMenu groupMenu, SummaryMenu summaryMenu) {
        this.customerMenu = customerMenu;
        this.groupMenu = groupMenu;
        this.summaryMenu = summaryMenu;

    }

    public static MenuImpl getInstance(){
        if(instance == null){
            instance = new MenuImpl(CustomerMenu.getInstance(), GroupMenu.getInstance(), SummaryMenu.getInstance());
        }
        return instance;
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
            return 0;
        }
    }
    @Override
    public void selectMenu(int menu) throws IOException {
            switch (menu) {
                case 1:
                    customerMenu.selectMenu(customerMenu.showMenu());
                    break;
                case 2:
                    groupMenu.selectMenu(groupMenu.showMenu());
                    break;
                case 3:
                    summaryMenu.selectMenu(summaryMenu.showMenu());
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                default:
                    ExceptionManager.catchInputTypeMismatchException();
                    break;
            }}
}

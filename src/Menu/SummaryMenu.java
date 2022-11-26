package Menu;

import Customer.Customer;
import Customer.Customers;
import Exceptions.ExceptionManager;
import Group.Group;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import Group.Groups;
import Group.GroupType;
import Group.Parameter;

public class SummaryMenu implements Menu{
    private final Customers customers;
    private final Groups groups;
    static SummaryMenu instance;

    public static SummaryMenu getInstance() {
        if(instance == null){
            instance = new SummaryMenu(Customers.getInstance(), Groups.getInstance());
        }
        return instance;
    }

    private SummaryMenu(Customers customers, Groups groups) {
        this.customers = customers;
        this.groups = groups;
    }


    @Override
    public int showMenu() throws IOException {
        try {
            customers.classifyCustomerByGroups(groups);
            System.out.println("요약 조회 메뉴");
            System.out.println("1. 등급별 고객 목록");
            System.out.println("2. 등급별 고객 이름순 조회");
            System.out.println("3. 등급별 고객 목록 이용 금액별 순위");
            System.out.println("4. 등급별 고객 목록 이용 시간별 순위");
            System.out.println("메뉴를 선택하세요");
            return Integer.parseInt(inputString());
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
        return 0;
    }

    @Override
    public void selectMenu(int menu) throws IOException {
        switch(menu){
            case 1:
                getGradeCustomerListMenu();
                break;
            case 2:
                getGradeCustomerNameListMenu();
                break;
            case 3:
                getGradeCustomerSpendMoneyListMenu();
                break;
            case 4:
                getGradeCustomerSpendHourListMenu();
                break;
        }
    }

    private void getGradeCustomerSpendHourListMenu() throws IOException {
        System.out.println("1.오름차순 2.내림차순 정렬을 설정해주세요.");
        int sort = Integer.parseInt(inputString());
        if(sort ==1){
            System.out.println("NONE 등급");
            customers.printNoneGroupByClassifiedCustomersList(customers.sortBySpendHourAsc());
            System.out.println("GENERAL 등급");
            customers.printGeneralGroupByClassifiedCustomersList(customers.sortBySpendHourAsc());
            System.out.println("VIP 등급");
            customers.printVIPGroupByClassifiedCustomersList(customers.sortBySpendHourAsc());
            System.out.println("VVIP 등급");
            customers.printVVIPGroupByClassifiedCustomersList(customers.sortBySpendHourAsc());}

        else if (sort==2) {
            System.out.println("NONE 등급");
            customers.printNoneGroupByClassifiedCustomersList(customers.sortBySpendHourDesc());
            System.out.println("GENERAL 등급");
            customers.printGeneralGroupByClassifiedCustomersList(customers.sortBySpendHourDesc());
            System.out.println("VIP 등급");
            customers.printVIPGroupByClassifiedCustomersList(customers.sortBySpendHourDesc());
            System.out.println("VVIP 등급");
            customers.printVVIPGroupByClassifiedCustomersList(customers.sortBySpendHourDesc());
        }
        else{
            System.out.println("잘못된 입력입니다.");
        }
    }

    private void getGradeCustomerSpendMoneyListMenu() throws IOException {
        System.out.println("1.오름차순 2.내림차순 정렬을 설정해주세요.");
        int sort = Integer.parseInt(inputString());
        if(sort == 1) {
            System.out.println("NONE 등급");
            customers.printNoneGroupByClassifiedCustomersList(customers.sortBySpendMoneyAsc());
            System.out.println("GENERAL 등급");
            customers.printGeneralGroupByClassifiedCustomersList(customers.sortBySpendMoneyAsc());
            System.out.println("VIP 등급");
            customers.printVIPGroupByClassifiedCustomersList(customers.sortBySpendMoneyAsc());
            System.out.println("VVIP 등급");
            customers.printVVIPGroupByClassifiedCustomersList(customers.sortBySpendMoneyAsc());
        }
        else if(sort ==2) {
            System.out.println("NONE 등급");
            customers.printNoneGroupByClassifiedCustomersList(customers.sortBySpendMoneyDesc());
            System.out.println("GENERAL 등급");
            customers.printGeneralGroupByClassifiedCustomersList(customers.sortBySpendMoneyDesc());
            System.out.println("VIP 등급");
            customers.printVIPGroupByClassifiedCustomersList(customers.sortBySpendMoneyDesc());
            System.out.println("VVIP 등급");
            customers.printVVIPGroupByClassifiedCustomersList(customers.sortBySpendMoneyDesc());
        }
        else{
            System.out.println("잘못된 입력입니다.");
        }

    }

    private void getGradeCustomerNameListMenu() throws IOException {
        System.out.println("1.오름차순 2.내림차순 정렬을 설정해주세요.");
        int sort = Integer.parseInt(inputString());
        if (sort == 1) {
            System.out.println("NONE 등급");
            customers.printNoneGroupByClassifiedCustomersList(customers.sortByNameAsc());
            System.out.println("GENERAL 등급");
            customers.printGeneralGroupByClassifiedCustomersList(customers.sortByNameAsc());
            System.out.println("VIP 등급");
            customers.printVIPGroupByClassifiedCustomersList(customers.sortByNameAsc());
            System.out.println("VVIP 등급");
            customers.printVVIPGroupByClassifiedCustomersList(customers.sortByNameAsc());
        }
            else if (sort == 2) {
            System.out.println("NONE 등급");
            customers.printNoneGroupByClassifiedCustomersList(customers.sortByNameDesc());
            System.out.println("GENERAL 등급");
            customers.printGeneralGroupByClassifiedCustomersList(customers.sortByNameDesc());
            System.out.println("VIP 등급");
            customers.printVIPGroupByClassifiedCustomersList(customers.sortByNameDesc());
            System.out.println("VVIP 등급");
            customers.printVVIPGroupByClassifiedCustomersList(customers.sortByNameDesc());
        }
                else{
                    System.out.println("잘못된 입력입니다.");
                }
            }



    private void getGradeCustomerListMenu() {
        System.out.println("NONE 등급");
        customers.printNoneGroupByClassifiedCustomersList(customers.getCustomers());
        System.out.println("GENERAL 등급");
        customers.printGeneralGroupByClassifiedCustomersList(customers.getCustomers());
        System.out.println("VIP 등급");
        customers.printVIPGroupByClassifiedCustomersList(customers.getCustomers());
        System.out.println("VVIP 등급");
        customers.printVVIPGroupByClassifiedCustomersList(customers.getCustomers());
    }







}

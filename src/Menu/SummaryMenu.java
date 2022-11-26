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

public class SummaryMenu extends MenuImpl{
    private final Customers customers;
    private final Groups groups;
    static SummaryMenu instance;

    public static SummaryMenu getInstance() {
        if(instance == null){
            instance = new SummaryMenu(Customers.getInstance(), Groups.getInstance());
        }
        return instance;
    }

    private SummaryMenu(Customers customers1, Groups groups1) {
        this.customers = customers1;
        this.groups = groups1;
    }


    @Override
    public int showMenu() throws IOException {
        try {
            classifyCustomerByGroups(groups);
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
            printNoneGroup(sortBySpendHourAsc());
            System.out.println("GENERAL 등급");
            printGeneralGroup(sortBySpendHourAsc());
            System.out.println("VIP 등급");
            printVIPGroup(sortBySpendHourAsc());
            System.out.println("VVIP 등급");
            printVVIPGroup(sortBySpendHourAsc());}

        else if (sort==2) {
            System.out.println("NONE 등급");
            printNoneGroup(sortBySpendHourDesc());
            System.out.println("GENERAL 등급");
            printGeneralGroup(sortBySpendHourDesc());
            System.out.println("VIP 등급");
            printVIPGroup(sortBySpendHourDesc());
            System.out.println("VVIP 등급");
            printVVIPGroup(sortBySpendHourDesc());
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
            printNoneGroup(sortBySpendMoneyAsc());
            System.out.println("GENERAL 등급");
            printGeneralGroup(sortBySpendMoneyAsc());
            System.out.println("VIP 등급");
            printVIPGroup(sortBySpendMoneyAsc());
            System.out.println("VVIP 등급");
            printVVIPGroup(sortBySpendMoneyAsc());
        }
        else if(sort ==2) {
            System.out.println("NONE 등급");
            printNoneGroup(sortBySpendMoneyDesc());
            System.out.println("GENERAL 등급");
            printGeneralGroup(sortBySpendMoneyDesc());
            System.out.println("VIP 등급");
            printVIPGroup(sortBySpendMoneyDesc());
            System.out.println("VVIP 등급");
            printVVIPGroup(sortBySpendMoneyDesc());
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
            printNoneGroup(sortByNameAsc());
            System.out.println("GENERAL 등급");
            printGeneralGroup(sortByNameAsc());
            System.out.println("VIP 등급");
            printVIPGroup(sortByNameAsc());
            System.out.println("VVIP 등급");
            printVVIPGroup(sortByNameAsc());
        }
            else if (sort == 2) {
            System.out.println("NONE 등급");
            printNoneGroup(sortByNameDesc());
            System.out.println("GENERAL 등급");
            printGeneralGroup(sortByNameDesc());
            System.out.println("VIP 등급");
            printVIPGroup(sortByNameDesc());
            System.out.println("VVIP 등급");
            printVVIPGroup(sortByNameDesc());
        }
                else{
                    System.out.println("잘못된 입력입니다.");
                }
            }



    private void getGradeCustomerListMenu() {
        System.out.println("NONE 등급");
        printNoneGroup(customers.getCustomers());
        System.out.println("GENERAL 등급");
        printGeneralGroup(customers.getCustomers());
        System.out.println("VIP 등급");
        printVIPGroup(customers.getCustomers());
        System.out.println("VVIP 등급");
        printVVIPGroup(customers.getCustomers());
    }



    public Customer[] sortByNameAsc() {
            Customer[] customers1 = customers.getCustomers();
            Arrays.sort(customers1, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    if (o1 != null && o2 != null) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return 0;
                }
            });
            return customers1;
    }

    public Customer[] sortByNameDesc() {
        Customer[] customers1 = customers.getCustomers();

        Arrays.sort(customers1, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    if (o1 != null && o2 != null) {
                        return o2.getName().compareTo(o1.getName());
                    }
                    return 0;
                }
            });
            return customers1;
        }



    public Customer[] sortBySpendMoneyDesc() {
        Customer[] customers1 = customers.getCustomers();

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o2.getSpendMoney() - o1.getSpendMoney();
                } else {
                    return 0;
                }
            }
        });
        return customers1;
    }

    public Customer[] sortBySpendMoneyAsc() {
        Customer[] customers1 = customers.getCustomers();

        Arrays.sort(customers1, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    if (o1 != null && o2 != null) {
                        return o1.getSpendMoney()- o2.getSpendMoney() ;
                    } else {
                        return 0;
                    }
                }
            });
            return customers1;
        }
    public Customer[] sortBySpendHourDesc() {
        Customer[] customers1 = customers.getCustomers();

            Arrays.sort(customers1, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    if (o1 != null && o2 != null) {
                        return o2.getUseHour() - o1.getUseHour();
                    } else {
                        return 0;

                    }
                }
            });
            return customers1;
        }

    public Customer[] sortBySpendHourAsc() {
        Customer[] customers1 = customers.getCustomers();

        Arrays.sort(customers1, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    if (o1 != null && o2 != null) {
                        return o1.getUseHour()- o2.getUseHour();
                    } else {
                        return 0;

                    }
                }
            });
            return customers1;
        }



    public void printNoneGroup(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 0) {
                    System.out.println(customers[i].toString());
                }
            }
        }
    }

    public void printGeneralGroup(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 1) {
                    System.out.println(customers[i].toString());
                }
            }
        }
    }

    public void printVIPGroup(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 2) {
                    System.out.println(customers[i].toString());
                }
            }
        }
    }

    public void printVVIPGroup(Customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null) {
                if (customers[i].getGroup().getGroupType().ordinal() == 3) {
                    System.out.println(customers[i].toString());
                }
            }
        }
    }

    public void initCustomersGroup() {
        for (int i = 0; i < customers.getCustomerCount(); i++) {
            if (customers.getCustomers()[i] != null) {
                customers.getCustomers()[i].setGroup(Group.of(Parameter.of(0, 0), 0));
            }
        }
    }

    public void classifyCustomerByGroups(Groups groups) {
        initCustomersGroup();
        for (int i = 3; i > 0; i--) {
            if (groups.getGroups()[i].getParameter().getSpendHourStandard() != 0 && groups.getGroups()[i].getParameter().getSpendMoneyStandard() != 0) {
                for (int j = 0; j < customers.getCustomerCount(); j++) {
                    if(customers.getCustomers()[j]!=null){
                        if(customers.getCustomers()[j].getGroup().getGroupType()== GroupType.NONE){
                            if (customers.getCustomers()[j].getUseHour() >= groups.getGroups()[i].getParameter().getSpendHourStandard() && customers.getCustomers()[j].getSpendMoney() >= groups.getGroups()[i].getParameter().getSpendMoneyStandard()) {
                                customers.getCustomers()[j].setGroup(groups.getGroups()[i]);
                            }}
                    }
                }
            }
        }
    }


}

package Menu;

import Customer.Customers;
import Customer.ClassifiedCustomers;
import Utils.ExceptionManager;

import java.io.IOException;

import Group.Groups;


public class SummaryMenu implements Menu {
    private final Customers customers;
    private final Groups groups;
    private final ClassifiedCustomers classifiedCustomers;
    static SummaryMenu instance;


    public static SummaryMenu getInstance() {
        if (instance == null) {
            instance = new SummaryMenu(Customers.getInstance(), Groups.getInstance(),ClassifiedCustomers.getInstance());
        }
        return instance;
    }

    private SummaryMenu(Customers customers, Groups groups, ClassifiedCustomers classifiedCustomers) {
        this.customers = customers;
        this.groups = groups;
        this.classifiedCustomers = classifiedCustomers;
    }


    @Override
    public int showMenu() throws IOException {
        try {
            if(!groups.checkIsGroupInit()){
                System.out.println("그룹이 초기화 되지 않았습니다.");
                return 0;
            }
            else if(customers.isEmpty()){
                System.out.println("고객이 없습니다.");
                return 0;
            }
            else {
                customers.classifyCustomerByGroups(groups);
                customers.groupByGroup(groups);
                System.out.println("요약 조회 메뉴");
                System.out.println("1. 등급별 고객 목록");
                System.out.println("2. 등급별 고객 이름순 조회");
                System.out.println("3. 등급별 고객 목록 이용 금액별 순위");
                System.out.println("4. 등급별 고객 목록 이용 시간별 순위");
                System.out.println("메뉴를 선택하세요");
                return Integer.parseInt(inputString());
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void selectMenu(int menu) throws IOException {
        switch (menu) {
            case 1:
                classifiedCustomers.printByGroup();
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
            default:
                ExceptionManager.catchInputTypeMismatchException();
                break;
        }
    }

    private void getGradeCustomerSpendHourListMenu() throws IOException {
        try{
        System.out.println("1.오름차순 2.내림차순 정렬을 설정해주세요.");
        int sort = Integer.parseInt(inputString());
        if (sort == 1) {
            classifiedCustomers.printBySortedDetailNONE(classifiedCustomers.sortBySpendHourAsc(classifiedCustomers.getNONEGroup()));
            classifiedCustomers.printBySortedDetailGENERAL(classifiedCustomers.sortBySpendHourAsc(classifiedCustomers.getGENERALGroup()));
            classifiedCustomers.printBySortedDetailVIP(classifiedCustomers.sortBySpendHourAsc(classifiedCustomers.getVIPGroup()));
            classifiedCustomers.printBySortedDetailVVIP(classifiedCustomers.sortBySpendHourAsc(classifiedCustomers.getVVIPGroup()));
        } else if (sort == 2) {
            classifiedCustomers.printBySortedDetailNONE(classifiedCustomers.sortBySpendHourDesc(classifiedCustomers.getNONEGroup()));
            classifiedCustomers.printBySortedDetailGENERAL(classifiedCustomers.sortBySpendHourDesc(classifiedCustomers.getGENERALGroup()));
            classifiedCustomers.printBySortedDetailVIP(classifiedCustomers.sortBySpendHourDesc(classifiedCustomers.getVIPGroup()));
            classifiedCustomers.printBySortedDetailVVIP(classifiedCustomers.sortBySpendHourDesc(classifiedCustomers.getVVIPGroup()));
        } else {
            System.out.println("번호를 잘못입력하셨습니다.");
        }}
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
    }

    private void getGradeCustomerSpendMoneyListMenu() throws IOException {
        try {
            System.out.println("1.오름차순 2.내림차순 정렬을 설정해주세요.");
            int sort = Integer.parseInt(inputString());
            if (sort == 1) {
                classifiedCustomers.printBySortedDetailNONE(classifiedCustomers.sortBySpendMoneyAsc(classifiedCustomers.getNONEGroup()));
                classifiedCustomers.printBySortedDetailGENERAL(classifiedCustomers.sortBySpendMoneyAsc(classifiedCustomers.getGENERALGroup()));
                classifiedCustomers.printBySortedDetailVIP(classifiedCustomers.sortBySpendMoneyAsc(classifiedCustomers.getVIPGroup()));
                classifiedCustomers.printBySortedDetailVVIP(classifiedCustomers.sortBySpendMoneyAsc(classifiedCustomers.getVVIPGroup()));
            } else if (sort == 2) {
                classifiedCustomers.printBySortedDetailNONE(classifiedCustomers.sortBySpendMoneyDesc(classifiedCustomers.getNONEGroup()));
                classifiedCustomers.printBySortedDetailGENERAL(classifiedCustomers.sortBySpendMoneyDesc(classifiedCustomers.getGENERALGroup()));
                classifiedCustomers.printBySortedDetailVIP(classifiedCustomers.sortBySpendMoneyDesc(classifiedCustomers.getVIPGroup()));
                classifiedCustomers.printBySortedDetailVVIP(classifiedCustomers.sortBySpendMoneyDesc(classifiedCustomers.getVVIPGroup()));
            } else {
                System.out.println("번호를 잘못입력하셨습니다.");
            }
        } catch (NumberFormatException e) {
            ExceptionManager.catchInputTypeMismatchException();
        }

    }

    private void getGradeCustomerNameListMenu() throws IOException {
        try {
            System.out.println("1.오름차순 2.내림차순 정렬을 설정해주세요.");
            int sort = Integer.parseInt(inputString());
            if (sort == 1) {
                classifiedCustomers.printBySortedDetailNONE(classifiedCustomers.sortByNameAsc(classifiedCustomers.getNONEGroup()));
                classifiedCustomers.printBySortedDetailGENERAL(classifiedCustomers.sortByNameAsc(classifiedCustomers.getGENERALGroup()));
                classifiedCustomers.printBySortedDetailVIP(classifiedCustomers.sortByNameAsc(classifiedCustomers.getVIPGroup()));
                classifiedCustomers.printBySortedDetailVVIP(classifiedCustomers.sortByNameAsc(classifiedCustomers.getVVIPGroup()));
            } else if (sort == 2) {
                classifiedCustomers.printBySortedDetailNONE(classifiedCustomers.sortByNameDesc(classifiedCustomers.getNONEGroup()));
                classifiedCustomers.printBySortedDetailGENERAL(classifiedCustomers.sortByNameDesc(classifiedCustomers.getGENERALGroup()));
                classifiedCustomers.printBySortedDetailVIP(classifiedCustomers.sortByNameDesc(classifiedCustomers.getVIPGroup()));
                classifiedCustomers.printBySortedDetailVVIP(classifiedCustomers.sortByNameDesc(classifiedCustomers.getVVIPGroup()));
            } else {
                System.out.println("번호를 잘못입력하셨습니다.");
            }
        } catch (NumberFormatException e) {
            ExceptionManager.catchInputTypeMismatchException();
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

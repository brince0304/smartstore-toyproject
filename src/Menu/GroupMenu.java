package Menu;

import Customer.Customers;
import Exceptions.ExceptionManager;
import Group.Groups;
import Group.Parameter;

import java.io.IOException;

public class GroupMenu implements Menu {
    static GroupMenu instance;

    private final Groups groups;
    private final Customers customers;

    private GroupMenu(Groups groups, Customers customers) {
        this.groups = groups;
        this.customers = customers;
    }

    static GroupMenu getInstance(){
        if(instance == null){
            instance = new GroupMenu(Groups.getInstance(), Customers.getInstance());
        }
        return instance;
    }


    @Override
    public int showMenu() throws IOException {
        try {
            System.out.println("그룹 관리 메뉴");
            System.out.println("1. 분류 기준 설정");
            System.out.println("2. 분류 기준 수정");
            System.out.println("3. 분류 기준 조회");
            System.out.println("3. 메인 메뉴로 돌아가기");
            System.out.println("메뉴를 선택하세요");
            return Integer.parseInt(inputString());
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
            return 0;
        }

    }

    @Override
    public void selectMenu(int menu) throws IOException {
        switch(menu){
            case 1:
                initGroupsStandard();
                break;
            case 2:
                updateGroupsStandard();
                break;
            case 3:
                printGroupsStandard();
                break;
            case 4:
                break;
        }

    }

    private void printGroupsStandard() throws IOException {
        try {
            if (checkIsGroupInit(groups)) {
                int grade =getGroupIndexFromInput();
                if (validateInput(grade)) {
                    groups.getGroupByIndex(grade);
                }
            }
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
    }


    private void updateGroupsStandard() throws IOException {
        try {
            if (checkIsGroupInit(groups)) {
                int grade = getGroupIndexFromInput();
                if (validateInput(grade)) {
                    Parameter parameter = buildParameter();
                    if (groups.updateGroupByIndex(grade, parameter)) {
                        System.out.println("등급 기준 수정이 완료되었습니다.");
                    } else {
                        System.out.println("등급 기준 수정에 실패했습니다.");
                    }
                } else {
                    System.out.println("잘못된 입력입니다.");
                }
            } else {
                System.out.println("분류 기준을 먼저 설정해주세요.");
            }
        }
        catch (NumberFormatException e) {
            ExceptionManager.catchInputTypeMismatchException();
        }
        }


    private void initGroupsStandard() throws IOException {
        try{
            int grade = getGroupIndexFromInput();
        if(validateInput(grade)) {
            Parameter parameter = buildParameter();
            if (groups.addGroup(grade, parameter)) {
                System.out.println("분류 기준이 설정되었습니다.");
            } else {
                System.out.println("실패했습니다.");
            }
        }
        else{
            System.out.println("잘못된 입력입니다.");
        }
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
    }

    private boolean checkIsGroupInit(Groups groups){
        for(int i=3; i>0; i--){
            if(groups.getGroups()[i].getParameter().getSpendHourStandard() ==0 && groups.getGroups()[i].getParameter().getSpendMoneyStandard() ==0){
                return false;
            }
        }
        return true;
    }

    public boolean validateInput(int input){
        if(input ==1 || input ==2 || input ==3){
            return true;
        }
        return false;
    }

    public Parameter buildParameter() throws IOException {
        try {
            System.out.println("분류 기준을 입력해주세요. 시간/만원 단위로 입력해주세요.");
            System.out.println("이용 시간을 입력해주세요.");
            int spendHour = Integer.parseInt(inputString());
            System.out.println("이용 금액을 입력해주세요.");
            int spendMoney = Integer.parseInt(inputString());
            return Parameter.of(spendHour, spendMoney);
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
            return null;
        }
    }

    public int getGroupIndexFromInput() throws IOException {
        try {
            System.out.println("해당 그룹의 등급을 입력해주세요.");
            System.out.println("1. 일반");
            System.out.println("2. VIP");
            System.out.println("3. VVIP");
            return Integer.parseInt(inputString());
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
            return -1;
        }
    }




}

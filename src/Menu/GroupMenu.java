package Menu;

import Customer.Customers;
import Exceptions.ExceptionManager;
import Group.Group;
import Group.Groups;
import Group.Parameter;

import java.io.IOException;

public class GroupMenu extends MenuImpl {
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
                classifyStandardMenu();
                break;
            case 2:
                updateClassifyMenu();
                break;
            case 3:
                getClassifyStandardMenu();
                break;
            case 4:
                break;
        }

    }

    private void getClassifyStandardMenu() throws IOException {
        try {
            if (isInit(groups)) {
                System.out.println("조회를 원하는 그룹 번호를 입력해주세요.");
                System.out.println("1. 일반");
                System.out.println("2. VIP");
                System.out.println("3. VVIP");
                String grade = inputString();
                if (inputValidator(grade)) {
                    groups.getGroup(Integer.parseInt(grade));
                }
            }
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
    }


    private void updateClassifyMenu() throws IOException {
        try {
            if (isInit(groups)) {
                System.out.println("해당 그룹의 등급을 입력해주세요.");
                System.out.println("1. 일반");
                System.out.println("2. VIP");
                System.out.println("3. VVIP");
                String grade = inputString();
                if (inputValidator(grade)) {
                    System.out.println("입력 원/시간 기준을 입력해주세요.");
                    System.out.println("기준 이용 시간을 시간단위로 입력해주세요");
                    int spendHour = Integer.parseInt(inputString());
                    System.out.println("기준 이용 금액을 만원 단위로 입력해주세요");
                    int spendMoney = Integer.parseInt(inputString());
                    Parameter parameter = Parameter.of(spendHour, spendMoney);
                    if (groups.updateGroup(Integer.parseInt(grade), parameter)) {
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


    private void classifyStandardMenu() throws IOException {
        try{
        System.out.println("해당 그룹의 등급을 설정해주세요.");
        System.out.println("1. 일반");
        System.out.println("2. VIP");
        System.out.println("3. VVIP");
        String grade = inputString();
        if(inputValidator(grade)) {
            System.out.println("분류 기준을 입력해주세요. 입력 원/시간 이상의 그룹을 생성합니다.");
            System.out.println("기준 이용 시간을 설정해주세요.");
            int spendHourStandard = Integer.parseInt(inputString());
            System.out.println("기준 이용 금액을 설정해주세요.");
            int spendMoneyStandard = Integer.parseInt(inputString());
            Parameter parameter = Parameter.of(spendHourStandard, spendMoneyStandard);
            if (groups.addGroup(Integer.parseInt(grade), parameter)) {
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

    private boolean isInit(Groups groups){
        for(int i=3; i>0; i--){
            if(groups.getGroups()[i].getParameter().getSpendHourStandard() ==0 && groups.getGroups()[i].getParameter().getSpendMoneyStandard() ==0){
                return false;
            }
        }
        return true;
    }

    public boolean inputValidator(String input){
        if(input.equals("1") || input.equals("2") || input.equals("3")){
            return true;
        }
        return false;
    }




}

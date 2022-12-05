package Menu;

import Utils.ExceptionManager;
import Group.Groups;
import Group.Parameter;

import java.io.IOException;

public class GroupMenu implements Menu {
    static GroupMenu instance;

    private final Groups groups;

    private GroupMenu(Groups groups) {
        this.groups = groups;
    }

    static GroupMenu getInstance(){
        if(instance == null){
            instance = new GroupMenu(Groups.getInstance());
        }
        return instance;
    }


    @Override
    public int showMenu() throws IOException {
        try {
            System.out.println("그룹 관리 메뉴");
            System.out.println("1. 분류 기준 추가");
            System.out.println("2. 분류 기준 수정");
            System.out.println("3. 분류 기준 조회");
            System.out.println("4. 메인 메뉴로 돌아가기");
            System.out.println("메뉴를 선택하세요");
            return Integer.parseInt(inputString());
        }
        catch (NumberFormatException e){
            return 0;
        }

    }

    @Override
    public void selectMenu(int menu) throws IOException {
        switch(menu){
            case 1:
                if(!groups.checkGroupsInit()) {
                    System.out.println("초기화가 되지 않은 그룹의 기준을 설정합니다.");
                    while(true){
                        if(!initGroupStandard()){
                            break;
                        }
                    }
                }
                else{
                    System.out.println("이미 그룹의 기준이 설정되어 있습니다.");
                }
                break;
            case 2:
                updateGroupStandard();
                break;
            case 3:
                printGroupStandard();
                break;
            case 4:
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }

    }

    private void printGroupStandard() throws IOException {
        try {
            if (groups.checkGroupsInit()) {
                System.out.println("그룹의 기준을 출력합니다.");
                System.out.println("1. GENERAL");
                System.out.println("2. VIP");
                System.out.println("3. VVIP");
                int grade = Integer.parseInt(inputString());
                if (validateInput(grade)) {
                    groups.getGroupByIndex(grade);
                }
            }
            else{
                System.out.println("그룹이 초기화 되지 않았습니다.");
            }
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
    }


    private void updateGroupStandard() throws IOException {
        try {
            if (groups.checkGroupsInit()) {
                System.out.println("수정을 원하는 그룹을 선택해주세요.");
                System.out.println("1. GENERAL");
                System.out.println("2. VIP");
                System.out.println("3. VVIP");
                int grade = Integer.parseInt(inputString());
                if (validateInput(grade)) {
                    Parameter parameter = create();
                    if(parameter!=null){
                    if (groups.updateGroupByIndex(grade, parameter)) {
                        System.out.println("등급 기준 수정이 완료되었습니다.");
                    } else {
                        System.out.println("실패했습니다. 다른 그룹과 기준 범위가 충돌합니다.");
                    }
                }
            } else {
                    System.out.println("잘못된 입력입니다.");
                }
            }
            else{
                System.out.println("그룹이 초기화 되지 않았습니다.");
            }
}
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
    }



    private boolean initGroupStandard() throws IOException {
        try{
            int grade = getGroupIndexFromInput();
            if(grade==0){
                return false;
            }
            Parameter parameter = create();
            if(parameter!=null) {
                if (groups.addGroup(grade, parameter)) {
                    System.out.println("분류 기준이 설정되었습니다.");
                } else {
                    System.out.println("등급의 기준 범위가 충돌하거나 잘못된 입력입니다.");
                }
                return true;
            }
            else{
                System.out.println("메뉴로 돌아갑니다.");
                return false;
            }
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
        return false;
    }



    public boolean validateInput(int input){
        return input == 1 || input == 2 || input == 3;
    }

    public Parameter create() throws IOException {
        try {
            System.out.println("분류 기준을 시간/만원 단위로 입력해주세요.");
            System.out.println("메뉴로 돌아가려면 0을 입력해주세요.");
            System.out.println("이용 시간을 입력해주세요.");
            int spendHour = Integer.parseInt(inputString());
            if(spendHour == 0){
                return null;
            }
            System.out.println("이용 금액을 입력해주세요.");
            int spendMoney = Integer.parseInt(inputString());
            if(spendMoney == 0){
                return null;
            }
            return Parameter.of(spendHour, spendMoney);
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
            return null;
        }
    }

    public int getGroupIndexFromInput() throws IOException {
        try {
            if(groups.checkOtherGroupIsInit(1)){
                System.out.println("GENERAL 등급의 기준을 입력해주세요.");
                return 1;
            }
            if(groups.checkOtherGroupIsInit(2)){
                System.out.println("VIP 등급의 기준을 입력해주세요.");
                return 2;
            }
            if(groups.checkOtherGroupIsInit(3)){
                System.out.println("VVIP 등급의 기준을 입력해주세요.");
                return 3;
            }
            return 0;
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
            return -1;
        }
    }




}

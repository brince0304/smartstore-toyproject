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
            System.out.println("1. 분류 기준 설정");
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
                initGroupStandard();
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
            if (groups.checkIsGroupInit()) {
                int grade =getGroupIndexFromInput();
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
            if (groups.checkIsGroupInit()) {
                int grade = getGroupIndexFromInput();
                if (validateInput(grade)) {
                    Parameter parameter = create();
                    if(parameter!=null){
                    if (groups.updateGroupByIndex(grade, parameter)) {
                        System.out.println("등급 기준 수정이 완료되었습니다.");
                    } }else {
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


    private void initGroupStandard() throws IOException {
        try{
            int grade = getGroupIndexFromInput();
        if(validateInput(grade)) {
            Parameter parameter = create();
            if(parameter!=null){
            if (groups.addGroup(grade, parameter)) {
                System.out.println("분류 기준이 설정되었습니다.");
            }} else {
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



    public boolean validateInput(int input){
        return input == 1 || input == 2 || input == 3;
    }

    public Parameter create() throws IOException {
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
            System.out.println("1. GENERAL");
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

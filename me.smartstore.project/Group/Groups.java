package Group;


import java.util.Arrays;

public class Groups {
    static Groups instance;
    private final Group[] groups;

    private Groups() {
        groups = new Group[4];
        groups[0] = Group.of(Parameter.of(0,0),0);
        groups[1] = Group.of(Parameter.of(0,0),1);
        groups[2] = Group.of(Parameter.of(0,0),2);
        groups[3] = Group.of(Parameter.of(0,0),3);
    }

    public static Groups getInstance() {
        if(instance == null){
            instance = new Groups();
        }
        return instance;
    }

public boolean addGroup(int grade,Parameter parameter) {
        if(checkGroupParameterIsAvailable(grade,parameter)) {
            groups[grade] = Group.of(parameter, grade);
            return true;
        }
        return false;
}


    public boolean updateGroupByIndex(int grade, Parameter parameter) {
       if(checkGroupParameterForUpdatingIsAvailable(grade,parameter)) {
           groups[grade] = Group.of(parameter, grade);
           return true;
       }
         return false;
    }



    public void getGroupByIndex(int inputString) {
        for (Group group : groups) {
            if (group.getGroupType().equals(GroupType.values()[inputString])) {
                System.out.println(group.toString());
            }
        }
    }
    public void printGroupList() {
        for (Group group : groups) {
            System.out.println(group.toString());
        }
    }

    public Group[] getGroups() {
      return groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groups)) return false;
        Groups groups1 = (Groups) o;
        return Arrays.equals(groups, groups1.groups);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(groups);
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groups=" + Arrays.toString(groups) +
                '}';
    }

    public boolean checkGroupsInit(){
        for(int i=3; i>0; i--){
            if(groups[i].getParameter().getSpendHourStandard() ==0 && groups[i].getParameter().getSpendMoneyStandard() ==0){
                return false;
            }
        }
        return true;
    }

    public boolean checkGroupParameterIsAvailable(int grade, Parameter parameter){
        if(parameter.getSpendHourStandard()<1 || parameter.getSpendMoneyStandard()<1){
            return false;
        }
        switch(grade){
            case 1:
                return parameter.getSpendHourStandard() >= 1 && parameter.getSpendMoneyStandard() >= 1;
            case 2:
                if(groups[1].getParameter().getSpendHourStandard() ==0 && groups[1].getParameter().getSpendMoneyStandard() ==0){
                    return false;
                }
                else{
                    return parameter.getSpendHourStandard() > groups[1].getParameter().getSpendHourStandard() && parameter.getSpendMoneyStandard() > groups[1].getParameter().getSpendMoneyStandard();
                }
            case 3:
                if((groups[2].getParameter().getSpendHourStandard() ==0 && groups[2].getParameter().getSpendMoneyStandard() ==0) || (groups[1].getParameter().getSpendHourStandard() ==0 && groups[1].getParameter().getSpendMoneyStandard() ==0)){
                    return false;
                }
                else{
                    return parameter.getSpendHourStandard() > groups[2].getParameter().getSpendHourStandard() && parameter.getSpendMoneyStandard() > groups[2].getParameter().getSpendMoneyStandard();
                }
            default:
                return false;
        }
    }

    public boolean checkOtherGroupIsInit(int grade){
        switch(grade){
            case 1:
                return groups[1].getParameter().getSpendHourStandard() ==0 && groups[1].getParameter().getSpendMoneyStandard() ==0;
            case 2:
                if(groups[1].getParameter().getSpendHourStandard() ==0 && groups[1].getParameter().getSpendMoneyStandard() ==0){
                    return false;
                }
                else{
                    return groups[2].getParameter().getSpendHourStandard() ==0 && groups[2].getParameter().getSpendMoneyStandard() ==0;
                }
            case 3:
                if((groups[2].getParameter().getSpendHourStandard() ==0 && groups[2].getParameter().getSpendMoneyStandard() ==0) || (groups[1].getParameter().getSpendHourStandard() ==0 && groups[1].getParameter().getSpendMoneyStandard() ==0)){
                    return false;
                }
                else{
                    return groups[3].getParameter().getSpendHourStandard() ==0 && groups[3].getParameter().getSpendMoneyStandard() ==0;
                }
            default:
                return false;
        }
    }

    public boolean checkGroupParameterForUpdatingIsAvailable(int grade,Parameter parameter){
        switch(grade){
            case 1:
                if(parameter.getSpendHourStandard() >= groups[2].getParameter().getSpendHourStandard() || parameter.getSpendMoneyStandard() >= groups[2].getParameter().getSpendMoneyStandard()){
                    return false;
                }
                else{
                    return parameter.getSpendHourStandard() >= 1 && parameter.getSpendMoneyStandard() >= 1;
                }
            case 2:
                if(parameter.getSpendHourStandard()<=groups[1].getParameter().getSpendHourStandard() || parameter.getSpendMoneyStandard()<=groups[1].getParameter().getSpendMoneyStandard()){
                    return false;
                }
                else{
                    return parameter.getSpendHourStandard() < groups[3].getParameter().getSpendHourStandard() || parameter.getSpendMoneyStandard() < groups[3].getParameter().getSpendMoneyStandard();
                }
            case 3:
                if(parameter.getSpendHourStandard()<=groups[2].getParameter().getSpendHourStandard() || parameter.getSpendMoneyStandard()<=groups[2].getParameter().getSpendMoneyStandard()){
                    return false;
                }
                else{
                    return parameter.getSpendHourStandard() > groups[2].getParameter().getSpendHourStandard() && parameter.getSpendMoneyStandard() > groups[2].getParameter().getSpendMoneyStandard();
                }
        }
        return false;
    }



}




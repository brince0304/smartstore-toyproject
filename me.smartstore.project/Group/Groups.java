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
        groups[grade] = Group.of(parameter,grade);
        return true;
}


    public boolean updateGroupByIndex(int grade, Parameter parameter) {
        for (Group group : groups) {
            if (group.getGroupType().equals(GroupType.values()[grade])) {
                group.setParameter(parameter);
                System.out.println("그룹이 수정되었습니다.");
                return true;
            }
        }
        return false;
    }



    public void getGroupByIndex(int inputString) {
        for (int i = 0; i < groups.length; i++) {
                if (groups[i].getGroupType().equals(GroupType.values()[inputString])) {
                    System.out.println(groups[i].toString());
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

    public boolean checkIsGroupInit(){
        for(int i=3; i>0; i--){
            if(groups[i].getParameter().getSpendHourStandard() ==0 && groups[i].getParameter().getSpendMoneyStandard() ==0){
                return false;
            }
        }
        return true;
    }

}




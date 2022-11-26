package Group;

import Customer.Customer;
import Customer.Customers;


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


    public boolean updateGroup(int grade, Parameter parameter) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].getGroupType().equals(GroupType.values()[grade])) {
                groups[i].setParameter(parameter);
                System.out.println("그룹이 수정되었습니다.");
                return true;
            }
        }
        return false;
    }

    public void getGroup(int inputString) {
        for (int i = 0; i < groups.length; i++) {
                if (groups[i].getGroupType().equals(GroupType.values()[inputString])) {
                    System.out.println(groups[i].toString());
                }
        }
    }
    public void printGroupList() {
        for (int i = 0; i < groups.length; i++) {
            System.out.println(groups[i].toString());
        }
    }

    public Group[] getGroups() {
      return groups;
    }


}




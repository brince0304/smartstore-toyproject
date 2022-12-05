package Group;

import Customer.Customer;

public class Group {
    private final GroupType grouptype;
    private Parameter parameter;

    public Group(GroupType grouptype, Parameter parameter) {
        this.grouptype = grouptype;
        this.parameter = parameter;
    }

    public static Group of(Parameter parameter,int grade) {
          GroupType grouptype = GroupType.values()[grade];
          return new Group(grouptype, parameter);
     }

    public GroupType getGroupType() {
        return grouptype;
    }

    @Override
    public String toString() {
        return
                "분류 그룹 :" + grouptype +
                "분류 기준 - 이용 기준 시간:" + parameter.getSpendHourStandard()+"시간, 이용 기준 금액:" + parameter.getSpendMoneyStandard()+"만원 \n";
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
package Customer;

import Group.Group;
import Group.Parameter;

import java.util.Objects;

public class Customer {
    private final String id;
    private String name;
    private String customerId;
    private int spendHour;
    private int spendMoney;

    private Group group ;

    private static int customerCount = 1;

    public Customer(String name, String customerId, int spendHour, int spendMoney) {
        this.id = String.format("%04d", customerCount++);
        this.name = name;
        this.customerId = customerId;
        this.spendHour = spendHour;
        this.spendMoney = spendMoney;
        this.group = Group.of(Parameter.of(0,0), 0);
    }


    public static Customer of(String name, String customerId, int spendHour, int spendMoney){
        return new Customer(name,customerId,spendHour,spendMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return
                "고유번호='" + id + '\'' +
                ", 이름='" + name + '\'' +
                ", 고객아이디='" + customerId + '\'' +
                ", 이용시간=" + spendHour +
                ", 이용금액=" + spendMoney +
                "만원, 등급=" + group.getGroupType();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }


    public static int getCustomerCount() {
        return customerCount;
    }

    public static void setCustomerCount(int customerCount) {
        Customer.customerCount = customerCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getSpendHour() {
        return spendHour;
    }

    public void setSpendHour(int spendHour) {
        this.spendHour = spendHour;
    }

    public int getSpendMoney() {
        return spendMoney;
    }

    public void setSpendMoney(int spendMoney) {
        this.spendMoney = spendMoney;
    }


    public String toStringWithoutType() {
        return
                "고유번호='" + id + '\'' +
                        ", 이름='" + name + '\'' +
                        ", 고객아이디='" + customerId + '\'' +
                        ", 이용시간=" + spendHour +
                        ", 이용금액=" + spendMoney;
    }
}

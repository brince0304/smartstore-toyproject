package Customer;

import Group.GroupType;

import java.util.Objects;

public class Customer {
    private String id;
    private String name;
    private String customerId;
    private int useHour;
    private int spendMoney;

    private GroupType groupType;



    private static int customerCount = 0;




    protected Customer(){};
    public Customer(String name, String customerId, int useHour, int spendMoney) {
        this.id = String.format("%04d", customerCount++);
        this.name = name;
        this.customerId = customerId;
        this.useHour = useHour;
        this.spendMoney = spendMoney;
    }

    public static Customer of(String name, String customerId, int useHour, int spendMoney){
        return new Customer(name,customerId,useHour,spendMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                ", useHour=" + useHour +
                ", spendMoney=" + spendMoney +
                ", groupType=" + groupType +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getUseHour() {
        return useHour;
    }

    public void setUseHour(int useHour) {
        this.useHour = useHour;
    }

    public int getSpendMoney() {
        return spendMoney;
    }

    public void setSpendMoney(int spendMoney) {
        this.spendMoney = spendMoney;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }
}

package Customer;

import Group.GroupType;

import static Group.Parameter.spendHourStandard;
import static Group.Parameter.spendMoneyStandard;

public class ClassifiedCustomers {

    static ClassifiedCustomers classifiedCustomers;
    private final Customer[] classifiedCustomersList;
    private int classifiedCustomerCount = 1;


    private ClassifiedCustomers(){
        classifiedCustomersList = new Customer[1000];
    }

    public static ClassifiedCustomers getInstance(){
        if(classifiedCustomers == null){
            classifiedCustomers = new ClassifiedCustomers();
        }
        return classifiedCustomers;
    }



    public void addClassifiedCustomer(Customer customer){
        classifiedCustomersList[classifiedCustomerCount++]=customer;
    }
    public void customerClassify(Customers customers){
        for(int i=0; i<Customer.getCustomerCount();i++){
            Customer customer = customers.getById(String.format("%04d", i++));
            classifyCustomer(customer);
            addClassifiedCustomer(customer);
        }
    }

    public void deleteCustomerById(String id) {
        for (int i = 0; i < this.classifiedCustomerCount; i++) {
            if (classifiedCustomersList[i].getId().equals(id)) {
                for (int j = i; j < classifiedCustomersList.length - 1; j++) {
                    classifiedCustomersList[j] = classifiedCustomersList[j + 1];
                }
                classifiedCustomerCount--;
                break;
            }
        }
    }
    public void classifyCustomer(Customer customer){
        if(customer.getSpendMoney()<spendHourStandard && customer.getSpendMoney()<spendMoneyStandard){
            customer.setGroupType(GroupType.GENERAL);
        }
        else if(customer.getSpendMoney()>=spendHourStandard*2 && customer.getSpendMoney()>=spendMoneyStandard*2){
            customer.setGroupType(GroupType.VVIP);
        }
        else if(customer.getSpendMoney()>=spendHourStandard && customer.getSpendMoney()>=spendMoneyStandard){
            customer.setGroupType(GroupType.VIP);
        }
    }

    public Customer[] getClassifiedCustomersList() {
        return classifiedCustomersList;
    }

    public int getClassifiedCustomerCount() {
        return classifiedCustomerCount;
    }

    public void setClassifiedCustomerCount(int classifiedCustomerCount) {
        this.classifiedCustomerCount = classifiedCustomerCount;
    }

    public static int getSpendHourStandard() {
        return spendHourStandard;
    }


    public static int getSpendMoneyStandard() {
        return spendMoneyStandard;
    }

}
